all: texCDC mvdoc pseudoclean

toggle:
	sed -i 's/Rapport/Pré-Rapport/g; s/Pré-Pré-//g' src/ressources/vocabulaire/vocabulaire.sty

texCDC:
	cd src; pdflatex CDC.tex;pdflatex CDC.tex; cd ..

mvdoc:
	mv src/*.pdf doc/

pseudoclean:
	rm -rf src/*.log src/*.aux src/*.toc src/*.out

clean: pseudoclean
	rm -rf doc/*.pdf
