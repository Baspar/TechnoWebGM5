#!/bin/bash
JAVA_PATH=$(find | grep \.java$ | cut -d/ -f2 | sort | uniq)

constr=""
meth=""
getter=""
setter=""
attribut=""

echoerr() { echo "$@" 1>&2; }

computeEnum() {
    line="$(echo "$1" | sed 's/[,;]$//g')"
    attribut="$attribut        $line\n"
}
compute() {
    line="$1"
    classname="$2"
    isAFunction=$(echo "$line" | grep "(" | sed '/^$/d')
    if ! [[ "$isAFunction" ]]
    then

        attrType=$(getAttrType "$line")

        attrOut=$(getAttrOut "$line")
        attrName=$(getAttrName "$line")

        #"static function" case
        if [[ $(echo "$line" | grep static) != "" ]]
        then
            attrName="{static}$attrName"
            line=$(echo $line | sed 's/static //g')
        fi

        attribut="$attribut        $attrType $attrName : $attrOut\n"
    else
        #Get state + remove
        state=$(getFunctionState "$line")
        line=$(echo $line | sed "s/\/\/.*//g")

        # get visibility + remove
        etat=$(getAttrType "$line")
        line=$(echo $line | cut -d' ' -f2-)

        #Get function/function name
        nomFonction=$(getFunctionName "$line")
        fonction=$(getFunction "$line")

        #"Abstract function" case
        if [[ $(echo "$line" | grep abstract) != "" ]]
        then
            fonction="{abstract}$fonction"
            line=$(echo $line | sed 's/abstract //g')
        fi
        #"static function" case
        if [[ $(echo "$line" | grep static) != "" ]]
        then
            fonction="{static}$fonction"
            line=$(echo $line | sed 's/static //g')
        fi

        if [ "$nomFonction" != "$classname" ]
        then
            out=$(getFunctionOut "$line")
        fi


        #Progress detection
        color=$(getFunctionColor "$state" "$fonction" "$classType")
        fonction="<color:$color>$fonction"
        out="$out</color>"

        #Append function to its correct list
        sortFunction "$etat" "$fonction" "$out" "$name"
    fi
}
getFunctionState() {
    echo -n "$1" | grep "//" | sed "s/.*\/\/\(.*\)/\1/g"
}
getFunction() {
    indice=$(echo -n "$1" | tr ' ' '\n' | grep -n '(' | head -n 1 | cut -d: -f1)
    echo -n "$1" | cut -d' ' -f$indice-
}
getFunctionOut() {
    indice=$(echo -n "$1" | tr ' ' '\n' | grep -n '(' | head -n 1 | cut -d: -f1)
    indice=$((indice-1))

    echo -n "$1" | cut -d' ' -f-$indice
}
getFunctionName() {
    echo -n "$1" | tr ' ' '\n' | grep "(" | head -n 1 | sed 's/(.*//g'
}
printFile() {
    classType="$1"
    name="$2"
    attribut="$3"
    contr="$4"
    setter="$5"
    getter="$6"
    meth="$7"
    md5sumJava="$8"

    echo "@startuml" > uml/$package.Classe$name.uml
    echo "$classType $name{" >> uml/$package.Classe$name.uml

    if [ "$attribut" != "" ]
    then
        if [[ $classType != "enum" ]]
        then
            echo "    ==<b>Attributes</b>==" >> uml/$package.Classe$name.uml
            echo -en "$attribut" | sed 's/\\\*/\*/g'|sort >> uml/$package.Classe$name.uml
        else
            echo -en "$attribut" | sed 's/\\\*/\*/g'>> uml/$package.Classe$name.uml
        fi
    fi
    if [ "$constr" != "" ]
    then
        echo "    ==<b>Constructors</b>==" >> uml/$package.Classe$name.uml
        echo -en "$constr" | sed 's/\\\*/\*/g'|sort >> uml/$package.Classe$name.uml
    fi
    if [ "$setter" != "" ]
    then
        echo "    'BEGINSETTER">> uml/$package.Classe$name.uml
        echo "    ==<b>Setters</b>==" >> uml/$package.Classe$name.uml
        echo -en "$setter" | sed 's/\\\*/\*/g'|sort >> uml/$package.Classe$name.uml
        echo "    'ENDSETTER">> uml/$package.Classe$name.uml
    fi
    if [ "$getter" != "" ]
    then
        echo "    'BEGINGETTER">> uml/$package.Classe$name.uml
        echo "    ==<b>Getters</b>==" >> uml/$package.Classe$name.uml
        echo -en "$getter" | sed 's/\\\*/\*/g'|sort >> uml/$package.Classe$name.uml
        echo "    'ENDGETTER">> uml/$package.Classe$name.uml
    fi
    if [ "$meth" != "" ]
    then
        echo "    ==<b>Methods</b>==" >> uml/$package.Classe$name.uml
        echo -en "$meth" | sed 's/\\\*/\*/g'|sort >> uml/$package.Classe$name.uml
        echo "    'END">> uml/$package.Classe$name.uml
    fi

    echo "}" >> uml/$package.Classe$name.uml
    echo "@enduml" >> uml/$package.Classe$name.uml
    echo "'$md5sumJava" >> uml/$package.Classe$name.uml
}
sortFunction() {
    nomFonction=$(echo "$fonction" | sed "s/.*>\(.*\)(.*/\1/g")
    if [ "$(echo $nomFonction | egrep "^$4")" ]
    then
        constr="$constr        $1$2</color>\n"
    elif [ "$(echo $nomFonction | grep -e "^set")" ]
    then
        setter="$setter        $1$2 : $3\n"
    elif [ "$(echo $nomFonction| grep -e "^get")" ]
    then
        getter="$getter        $1$2 : $3\n"
    else
        meth="$meth        $1$2 : $3\n"
    fi
}
getFunctionColor() {
    if [[ $(echo "$2" | grep abstract ) != "" ]] 
    then
        color="grey"
    elif [[ $(echo "$3" | grep abstract ) != "" || $(echo "$3" | grep interface ) != "" ]] 
    then
        color="grey"
    elif [[ "$1" == "" ]]
    then
        echoerr "    No progress for $2"
        color="black"
    elif [[ "$1" == "TODO" ]]
    then
        color="red"
    elif [[ "$1" == "WIP" ]]
    then
        color="orange"
    elif [[ "$1" == "DONE" ]]
    then
        color="green"
    elif [[ "$1" == "CHK" ]]
    then
        color="brown"
    else
        color="white"
        echoerr "    Error with method $2 ($1)"
    fi
    echo $color
}
getAttrName() {
    line=$(echo "$1" | sed "s/.* \([^ ]*\)$/\1/g")
    echo -n "$line"
}
getAttrOut() {
    line=$(echo "$1" | sed "s/[^ ]\+ \(.*\) [^ ]\+$/\1/g")
    echo -n "$line"
}
getAttrType() {
    out=$(echo "$1" | cut -d ' ' -f 1)
    if [[ $out == "protected" ]]
    then
        out='#'
    elif [[ $out == "private" ]]
    then
        out='-'
    else
        out='+'
    fi
    echo $out
}
getClassType() {
    isAbstract=$(echo "$1"| grep "abstract")
    isInterface=$(echo "$1" | grep "interface")
    isEnum=$(echo "$1" | grep "enum")
    if [[ $isAbstract ]]
    then
        out="abstract"
    elif [[ $isInterface ]]
    then
        out="interface"
    elif [[ $isEnum ]]
    then
        out="enum"
    else
        out="class"
    fi
    echo "$out"
}
preprocessTxt() {
    newFile="$1"

    #Main preprocess
    newFile="$(echo "$newFile"  | sed "s/\/\*[.\n]*?\*\///g;
                                        s/^[\t ]*//g;"\
                                | grep "^public\|^private\|^protected" \
                                | sed " s/{//g;
                                        s/final //g;
                                        s/ *\([,=]\) */\1/g;
                                        s/\(<(\) */\1/g;
                                        s/ *\(>)\)/\1/g;
                                        s/\/\/[^\(TODO\|CHK\|WIP\|DONE\)].*//g;
                                        s/;//g;"
                                        )"

    echo -e "$newFile"
}
isAlreadyTested() {
    name=$(basename -s .java $1)
    myMD5=$2
    package=$3
    if [ -e uml/$package.Classe$name.uml ]
    then
        dejaFait=$(cat uml/$package.Classe$name.uml | tail -n 2 | tr "\n" " " | grep "'$myMD5")
    else
        dejaFait=""
    fi
    echo "$dejaFait"
}


rm -rf uml/Classes.uml Classes.png
for i in $*
do
    [ "$i" == "clean" ] && rm -f uml/*Classe*.uml
    [ "$i" == "clear" ] && rm -f uml/*Classe*.uml && echo "UML files deleted" && exit 0
    [ "$i" == "noget" ] && noget='true'
    [ "$i" == "noset" ] && noset='true'
done

echo "@startuml" >> uml/Classes.uml
echo "!include skin.uml" >> uml/Classes.uml




for package in $JAVA_PATH
do
    echo "Package $package in analysis"
    nbFichier=$(ls $package/*.java |grep -v "Main" | wc -l)
    indic=0
    echo "package $package{" >> uml/Classes.uml
    for javaFile in $(ls $package/*.java | grep -v "Main")
    do
        name=$(basename -s .java $javaFile) # Nom du fichier sans extenstion
        preprocessedFile=$(preprocessTxt "$(cat "$javaFile")")
        md5sumJava=$(echo "$preprocessedFile" | md5sum)

        indic=$(($indic + 1)) # Numero du fichier traité
        echoerr -n "  [$indic/$nbFichier]" #debug

        if [ "$noget" ] && [ "$noset" ]
        then
            echo "!include ngns.$package.Classe$name.uml"  >> uml/Classes.uml
        elif [ "$noget" ]
        then
            echo "!include ng.$package.Classe$name.uml"  >> uml/Classes.uml
        elif [ "$noset" ]
        then
            echo "!include ns.$package.Classe$name.uml"  >> uml/Classes.uml
        else
            echo "!include $package.Classe$name.uml"  >> uml/Classes.uml
        fi


        if [[ "$(isAlreadyTested "$javaFile" "$md5sumJava" "$package")" ]]
        then
            echoerr "  (-)  Class $name"
        else
            echoerr "  (O)  Class $name" # Debug
            rm -f uml/$package.Classe$name.uml

            #Get the file content, and get its header and its declarations
            header=$(cat "$javaFile" | grep "interface \+$name\|class \+$name\|enum \+$name" | sed "s/\(class \)\?\+$name.*//g")
            preprocessedFile=$(echo "$preprocessedFile" | grep -v "interface\|class\|enum"  | sed '/^}$/d' )

            #Is the class abstract/an interface ?
            classType=$(getClassType "$header")

            constr=""
            meth=""
            getter=""
            setter=""
            attribut=""

            [[ "$preprocessedFile" ]] && while read line
            do
                #Sort every line(=Attribute/Method) in its corresponding group
                if [[ $classType == "enum" ]]
                then
                    computeEnum "$line"
                else
                    compute "$line" "$name"
                fi
            done <<< "$(echo "$preprocessedFile")"

            printFile "$classType" "$name" "$attribut" "$contr" "$setter" "$getter" "$meth" "$md5sumJava"

        fi
        if [ "$noget" ] && [ "$noset" ]
        then
            cat uml/$package.Classe$name.uml | tr '\n' '\f' | sed 's/BEGINSETTER.*ENDGETTER//g' | tr '\f' '\n'>> uml/ngns.$package.Classe$name.uml
        elif [ "$noget" ]
        then
            cat uml/$package.Classe$name.uml | tr '\n' '\f' | sed 's/BEGINGETTER.*ENDGETTER//g' | tr '\f' '\n'>> uml/ng.$package.Classe$name.uml
        elif [ "$noset" ]
        then
            cat uml/$package.Classe$name.uml | tr '\n' '\f' | sed 's/BEGINSETTER.*ENDSETTER//g' | tr '\f' '\n'>> uml/ns.$package.Classe$name.uml
        fi
    done
    echo "}" >> uml/Classes.uml
done


echo "!include links.uml" >> uml/Classes.uml
echo "@enduml" >> uml/Classes.uml
echo "UML created. PNG rendering in progress"
java -jar uml/plantuml.jar uml/Classes.uml
mv uml/Classes.png .

rm -rf uml/ngns.* uml/ng.* uml/ns.*

[ -e Classes.png ] && eog Classes.png || xdg-open Classes.png
