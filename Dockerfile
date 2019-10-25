FROM tomcat:jdk13-openjdk-oracle

RUN rm -rf /usr/local/tomcat/webapps/ROOT

ADD ./target/test-api.war /usr/local/tomcat/webapps/

RUN mv /usr/local/tomcat/webapps/test-api.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

CMD ["catalina.sh", "run"]