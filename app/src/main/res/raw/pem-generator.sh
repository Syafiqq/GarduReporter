#!/usr/bin/env bash

#based on http://blog.crazybob.org/2010/02/android-trusting-ssl-certificates.html

cd /home/syafiq/Documents/JetBrains/AndroidStudio/freelancer/syafiqq.freelancer.app/GarduReporter/app/src/main/res/raw
export MY_SERVER=gardureporter.000webhostapp.com
export CERT_STORE=${MY_SERVER//./_}_bks.bks
export PEM_STORE=${MY_SERVER//./_}_pem.pem
export CLASSPATH=bcprov-jdk15-157.jar
if [ -a ${PEM_STORE} ]; then
    rm ${PEM_STORE} || exit 1
fi
echo | openssl s_client -connect ${MY_SERVER}:443 2>&1 | sed -ne '/-BEGIN CERTIFICATE-/,/-END CERTIFICATE-/p' > ${PEM_STORE}
if [ -a ${CERT_STORE} ]; then
    rm ${CERT_STORE} || exit 1
fi
keytool \
      -import \
      -v \
      -trustcacerts \
      -alias 0 \
      -file <(openssl x509 -in ${PEM_STORE}) \
      -keystore ${CERT_STORE} \
      -storetype BKS \
      -provider org.bouncycastle.jce.provider.BouncyCastleProvider \
      -providerpath /usr/share/java/bcprov.jar \
      -storepass ez24get