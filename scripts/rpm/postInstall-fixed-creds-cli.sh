cp /opt/jboss/wildfly-app/lib/wildflyApp.war /opt/jboss/wildfly/standalone/deployments/
/opt/jboss/wildfly/bin/jboss-cli.sh -c <<EOF
module add --name=com.mysql --resources=/opt/jboss/wildfly-app/lib/mysql-connector-java-8.0.21.jar --dependencies=javax.api,javax.transaction.api
reload
/subsystem=datasources/jdbc-driver=mysql:add(driver-name=mysql,driver-module-name=com.mysql)
data-source add --jndi-name=java:/WildflyAppDS --name=MySQLPool --connection-url=jdbc:mysql://172.17.0.7:3306/mysqlschema --driver-name=mysql --user-name=jboss --password=jboss
EOF
rm -f /opt/jboss/wildfly/standalone/configuration/standalone_xml_history/current/*