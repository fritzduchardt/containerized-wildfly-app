set -e
cp /opt/jboss/wildfly-app/lib/mysql-connector-java-8.0.21.jar /mysql-connector-java-8.0.21.jar
cp /opt/jboss/wildfly-app/lib/wildflyApp.war /opt/jboss/wildfly/standalone/deployments/
cp /opt/jboss/wildfly-app/lib/standalone-fixed.xml /opt/jboss/wildfly/standalone/configuration/standalone.xml
mkdir -p /opt/jboss/wildfly/modules/system/layers/base/com/mysql/main && cp /opt/jboss/wildfly-app/lib/module.xml "$_"