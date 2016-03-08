all: graphs texRapport mvdoc pseudoclean

graphs:
	for i in src/graph/*.uml; do echo $$i; java -jar src/graph/plantuml.jar $$i; done

toggle:
	sed -i 's/Rapport/Pré-Rapport/g; s/Pré-Pré-//g' src/ressources/vocabulaire/vocabulaire.sty

texRapport:
	cd src; pdflatex Rapport.tex;pdflatex Rapport.tex; cd ..

mvdoc:
	mv src/*.pdf doc/

cleangraph:
	rm -rf src/graph/*.png

pseudoclean:
	rm -rf src/*.log src/*.aux src/*.toc src/*.out

clean: pseudoclean cleangraph
	rm -rf doc/*.pdf
