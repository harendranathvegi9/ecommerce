#!/bin/bash 

read -p "Choose an action (type [1]local, [2]remote): " SERVER
read -p "Choose an action (type [1]resource, [2]realm, [3]deploy, [4]undeploy, [5]database, [6]jms): " ACTION

if [ $SERVER -eq 1 ]
then
  CASEserver="local"
elif [ $SERVER -eq 2 ]
then
  CASEserver="remote"
else
  echo "Not found"
  CASEserver="end"
fi

if [ $ACTION -eq 1 ]
then
  CASEaction="resource"
elif [ $ACTION -eq 2 ]
then
  CASEaction="realm"
elif [ $ACTION -eq 3 ]
then
  CASEaction="deploy"
elif [ $ACTION -eq 4 ]
then
  CASEaction="undeploy"
elif [ $ACTION -eq 5 ]
then
  CASEaction="database"
elif [ $ACTION -eq 6 ]
then
  CASEaction="jms"
else
  echo "Not found"
  CASEaction="end"
fi


case $CASEserver in
  local)
    ASADMIN="/Users/cem/Developments/glassfish4/glassfish/bin/asadmin"
    MYSQL="/usr/local/Cellar/mysql/5.7.15/bin/mysql"
    GLASSFISH_RESOURCES="/Users/cem/Developments/NetBeansProjects/ecommerce/src/main/webapp/WEB-INF/glassfish-resources.xml"
    WAR="/Users/cem/Developments/NetBeansProjects/ecommerce/target/ecommerce-1.0-SNAPSHOT.war"
    SQL="/Users/cem/Developments/NetBeansProjects/ecommerce/src/main/resources/META-INF/load.sql"
    ;;
  remote)
    ASADMIN="/opt/glassfish4/bin/asadmin"
    MYSQL="/usr/bin/mysql"
    GLASSFISH_RESOURCES="/root/Developments/NetBeansProjects/ecommerce/src/main/webapp/WEB-INF/glassfish-resources.xml"
    WAR="/root/Developments/NetBeansProjects/ecommerce/target/ecommerce-1.0-SNAPSHOT.war"
    SQL="/root/Developments/NetBeansProjects/ecommerce/src/main/resources/META-INF/load.sql"
    ;;
esac


case $CASEaction in
  resource)
    $ASADMIN add-resources $GLASSFISH_RESOURCES
    ;;
  realm)
    USER_TABLE="USERENTITY"
    USERNAME="EMAIL"
    PASSWORD="PASSWORD"
    GROUP_TABLE="USERENTITY"
    GROUP_NAME="USERGROUP"
    DIGEST_ALGORITHM="none"
    PASSWORD_ENC_ALGORITHM="none"
    REALM_NAME="jdbcecommerce"
    JAAS_CONTEXT="jdbcRealm"
    DATASOURCE_JNDI="jdbc/ecommerce"
    $ASADMIN create-auth-realm --classname com.sun.enterprise.security.auth.realm.jdbc.JDBCRealm --property jaas-context=${JAAS_CONTEXT}:datasource-jndi=${DATASOURCE_JNDI}:user-table=${USER_TABLE}:user-name-column=${USERNAME}:password-column=${PASSWORD}:group-table=${GROUP_TABLE}:group-name-column=${GROUP_NAME}:digest-algorithm=${DIGEST_ALGORITHM}:digestrealm-password-enc-algorithm=${PASSWORD_ENC_ALGORITHM} ${REALM_NAME}
    ;;
  jms)
    $ASADMIN create-jms-resource --restype javax.jms.Queue --property Name=ecommerce jms/ecommerce
    ;;
  deploy)
    $ASADMIN deploy $WAR
    ;;
  undeploy)
    $ASADMIN undeploy ecommerce-1.0-SNAPSHOT
    ;;
  database)
    $MYSQL -u root -p --default-character-set=utf8 ecommerce < $SQL
    ;;
esac
