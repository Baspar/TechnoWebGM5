all: graphs texCDC mvdoc pseudoclean

graphs:
	for i in srcCDC/graph/*.uml; do echo $$i; java -jar srcCDC/graph/plantuml.jar $$i; done

toggle:
	sed -i 's/Rapport/Pré-Rapport/g; s/Pré-Pré-//g' srcCDC/ressources/vocabulaire/vocabulaire.sty

texCDC:
	cd srcCDC; pdflatex CDC.tex;pdflatex CDC.tex; cd ..

mvdoc:
	mv srcCDC/*.pdf doc/

cleangraph:
	rm -rf srcCDC/graph/*.png

pseudoclean:
	rm -rf srcCDC/*.log srcCDC/*.aux srcCDC/*.toc srcCDC/*.out

clean: pseudoclean cleangraph
	rm -rf doc/*.pdf
