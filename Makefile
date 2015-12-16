.PHONY: praktikum.pdf watch

praktikum.pdf: content/praktikum.md
	pandoc --template=template/praktikum.tex \
			--filter pandoc-crossref \
			--filter pandoc-citeproc \
			--listings \
			--chapter \
			content/praktikum.md \
			content/teil_1.md \
			content/teil_2.md \
			content/teil_3.md \
			content/teil_4.md \
			bibliography.md \
			-o "build/praktikum.pdf"
	xdg-open build/praktikum.pdf

watch:
	while true; do inotifywait -e close_write . && make praktikum.pdf; done
