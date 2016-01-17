#!/bin/bash


rm -rf Classes.uml Classes.png
[ "$1" == "clean" ] && rm -f Classe*.uml
[ "$1" == "clear" ] && rm -f Classe*.uml && echo "Suppression fichier OK" && exit 0
[ "$1" == "nocheck" ] && nocheck=1 || nocheck=0

echo "@startuml" >> Classes.uml
echo "!include skin.uml" >> Classes.uml
echo "!include liaisons.uml" >> Classes.uml



nbFichier=$(ls ../Java/*.java | wc -l)
indic=0

echo "Analyse des fichiers .java en cours"
for javaFile in $(ls ../Java/*.java)
do

    name=$(basename -s .java $javaFile) # Nom du fichier sans extenstion
    echo "!include Classe$name.uml"  >> Classes.uml

    indic=$(($indic + 1)) # Numero du fichier traité
    if [ $indic -lt 10 ]
    then
        echo -n "[ $indic/$nbFichier]" #debug
    else
        echo -n "[$indic/$nbFichier]" #debug
    fi

    md5sumJava=$(md5sum $javaFile)
    if [ -e Classe$name.uml ]
    then
        dejaFait=$(cat Classe$name.uml | tail -n 2 | tr "\n" " " | grep "'$md5sumJava")
    else
        dejaFait=""
    fi



    if [ "$dejaFait" == "" ]&&[ $nocheck -ne 1 ]
    then
        echo "  (O)  Classe $name" # Debug
        rm -f Classe$name.uml

        #Get the file content
        myFile=$(cat "$javaFile" | sed "s/\t/ /g;
                    s/ \+/ /g;
                    s/^ //g;
                    s/ \?\(\W\) \?/\1/g;
                    /^\/\//d")
        header=$(cat "$javaFile" | grep "class \+$name" | sed "s/class \+$name.*//g")

        #Is the class abstract/an interface ?
        isAbstract=$(echo "$header"| grep "abstract")
        isInterface=$(echo "$header" | grep "interface")
        if [[ $isAbstract ]]
        then
            type="abstract"
        elif [[ $isInterface ]]
        then
            type="interface"
        else
            type="class"
        fi

        #Catch only the methods/Attributes
        myFile=$(echo "$myFile" | grep "public\|protected\|private\|abstract\|$name(.*){" \
                                | sed "s/[;{]//g"\
                                | sed "s/\t/ /g; s/ \+/ /g; s/^ //g; /^$/d"\
                                | grep -v "class")

        constr=""
        meth=""
        getter=""
        setter=""
        attribut=""

        #TODO: move to the end
        #Ecriture fichier

        while read line
        do
            #echo "$line"
            isAFunction=$(echo "$line" | grep "(" | sed '/^$/d')
            if ! [[ "$isAFunction" ]]
            then
                attrType=$(echo "$line" | cut -d ' ' -f 1)
                if [[ $attrType == "protected" ]]
                then
                    state='~'
                elif [[ $attrType == "private" ]]
                then
                    state='-'
                else
                    state='+'
                fi

                attrOut=$(echo "$line" | cut -d ' ' -f 2)
                attrName=$(echo "$line" | cut -d ' ' -f 3)
                attribut="$attribut        $attrType $attrName : $attrOut\n"
                echo "    $state$attrName: $attrOut" >> Classe$name.uml
            else
                state=$(echo $line |grep "//" | sed "s/.*\/\/\(.*\)/\1/g")
                ligne=$(echo $line | sed "s/\/\/.*//g")
                #Cas Cosntructeur
                if [ "$(echo $ligne | egrep "^$name\(")" ]
                then
                    etat="+"
                    out=""
                    fonction="$ligne"
                    nomFonction="$name"
                else
                    #Detection du type de sortie et du nom de fonction
                    etat="+"
                    out=$(echo "$ligne"  | cut -d "(" -f 1 | tr ' ' '\n' | tail -n 2 | head -n 1)
                    nomFonction=$(echo "$ligne"  | cut -d "(" -f 1 | tr ' ' '\n' | tail -n 1)
                    fonction=$(echo "$ligne" | sed "s/.*$out \(.*\)/\1/1")

                    # On rajoute le abstract
                    if [[ $(echo "$ligne" | sed "s/$fonction//g" | grep abstract) != "" ]]
                    then
                        fonction="{abstract}$fonction"
                    fi
                fi

                #Detection de l'avancement du code
                if [[ $(echo "$fonction" | grep abstract ) != "" ]] 
                then
                    fonction="<color:grey>$fonction"
                    out="$out</color>"
                else
                    if [[ $state == "" ]]
                    then
                        echo "  Pas de progres pour $ligne"
                        fonction="<color:white>$fonction"
                        out="$out</color>"
                    else
                        if [[ $state == "TODO" ]]
                        then
                            fonction="<color:red>$fonction"
                            out="$out</color>"
                        elif [[ $state == "WIP" ]]
                        then
                            fonction="<color:orange>$fonction"
                            out="$out</color>"
                        elif [[ $state == "DONE" ]]
                        then
                            fonction="<color:green>$fonction"
                            out="$out</color>"
                        else
                            echo "    Erreur avec la méthode $ligne"
                        fi
                    fi
                fi

                #TODO: Move this line
                echo "    $etat$fonction : $out" >> Classe$name.uml

                #Ajout de la fonction à la bonne liste
                if [ "$(echo $nomFonction | egrep "^$name")" ]
                then
                    constr="$constr        $etat$fonction$out\n"
                elif [ "$(echo $nomFonction | grep -e "^set")" ]
                then
                    setter="$setter        $etat$fonction : $out\n"
                elif [ "$(echo $nomFonction| grep -e "^get")" ]
                then
                    getter="$getter        $etat$fonction : $out\n"
                else
                    meth="$meth        $etat$fonction : $out\n"
                fi
            fi
        done <<< "$(echo "$myFile")"




        echo "@startuml" > Classe$name.uml
        echo "$type $name{" >> Classe$name.uml

        if [ "$attribut" != "" ]
        then
            echo "    ==<b>Attributs</b>==" >> Classe$name.uml
            echo -en "$attribut" | sed 's/\\\*/\*/g'|sort >> Classe$name.uml
        fi
        if [ "$constr" != "" ]
        then
            echo "    ==<b>Constructeurs</b>==" >> Classe$name.uml
            echo -en "$constr" | sed 's/\\\*/\*/g'|sort >> Classe$name.uml
        fi
        if [ "$destr" != "" ]
        then
            echo "    ==<b>Destructeurs</b>==" >> Classe$name.uml
            echo -en "$destr" | sed 's/\\\*/\*/g'|sort >> Classe$name.uml
        fi
        if [ "$setter" != "" ]
        then
            echo "    ==<b>Setters</b>==" >> Classe$name.uml
            echo -en "$setter" | sed 's/\\\*/\*/g'|sort >> Classe$name.uml
        fi
        if [ "$getter" != "" ]
        then
            echo "    ==<b>Getters</b>==" >> Classe$name.uml
            echo -en "$getter" | sed 's/\\\*/\*/g'|sort >> Classe$name.uml
        fi
        if [ "$meth" != "" ]
        then
            echo "    ==<b>Methodes</b>==" >> Classe$name.uml
            echo -en "$meth" | sed 's/\\\*/\*/g'|sort >> Classe$name.uml
        fi

        echo "}" >> Classe$name.uml
        echo "@enduml" >> Classe$name.uml
        echo "'$md5sumJava" >> Classe$name.uml

    else
        echo "  (X)  Classe $name" # Debug
    fi
done

echo "@enduml" >> Classes.uml
echo "Fichier .uml generé. Création du fichier PNG en cours..."
java -jar plantuml.jar Classes.uml
rm Classes.uml

[ -e Classes.png ] && eog Classes.png || xdg-open Classes.png
