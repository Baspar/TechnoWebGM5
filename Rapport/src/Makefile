all: graphs texRapport mvdoc pseudoclean

graphs:
	for i in srcRapport/graph/*.uml; do echo $$i; java -jar srcRapport/graph/plantuml.jar $$i; done

toggle:
	sed -i 's/Rapport/Pré-Rapport/g; s/Pré-Pré-//g' srcRapport/ressources/vocabulaire/vocabulaire.sty

texRapport:
	cd srcRapport; pdflatex Rapport.tex;pdflatex Rapport.tex; cd ..

mvdoc:
	mv srcRapport/*.pdf doc/

cleangraph:
	rm -rf srcRapport/graph/*.png

pseudoclean:
	rm -rf srcRapport/*.log srcRapport/*.aux srcRapport/*.toc srcRapport/*.out

clean: pseudoclean cleangraph
	rm -rf doc/*.pdf
