UML=$(wildcard src/graph/*.uml)
PNG=$(UML:.uml=.png)

all: $(PNG) texCDC mvdoc pseudoclean

%.png: %.uml
	java -jar src/graph/plantuml.jar $<

toggle:
	sed -i 's/Rapport/Pré-Rapport/g; s/Pré-Pré-//g' src/ressources/vocabulaire/vocabulaire.sty

texCDC:
	cd src; pdflatex CDC.tex;pdflatex CDC.tex; cd ..

mvdoc:
	mv src/*.pdf doc/

cleangraph:
	rm -rf src/graph/*.png

pseudoclean:
	rm -rf src/*.log src/*.aux src/*.toc src/*.out

clean: pseudoclean cleangraph
	rm -rf doc/*.pdf
