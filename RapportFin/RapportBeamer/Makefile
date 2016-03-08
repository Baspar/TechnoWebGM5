all: graphs tex mvdoc pseudoclean

graphs:
	for i in src/graph/*.uml; do echo $$i; java -jar src/graph/plantuml.jar $$i; done

tex:
	cd src; pdflatex RapportBeamer.tex;pdflatex RapportBeamer.tex; cd ..

mvdoc:
	mv src/*.pdf doc/

cleangraph:
	rm -rf src/graph/*.png

pseudoclean:
	rm -rf src/*.log src/*.aux src/*.toc src/*.out src/*.nav src/*.snm

clean: pseudoclean cleangraph
	rm -rf doc/*.pdf
