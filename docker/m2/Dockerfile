FROM httpd
COPY ./website /usr/local/apache2/htdocs
COPY ./jar /usr/app/
RUN mkdir -p /usr/share/man/man1
RUN apt-get update -y && apt-get install default-jre -y
CMD /bin/sh -c /usr/local/apache2/bin/httpd -D FOREGROUND && /usr/app/start.sh
