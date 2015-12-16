default: ba.pdf

%.pdf: %.tex $(wildcard *.bib inhalt/* latex/* figures/* src/* plots/*)
	pdflatex $<
	bibtex $*
	pdflatex $<
	pdflatex $<
	pdflatex $<

clean:
	rm -rf *.{aux,log,toc,tdo,lof,lot,lol,thm,out,bbl,blg,dvi,ps,pdf}
