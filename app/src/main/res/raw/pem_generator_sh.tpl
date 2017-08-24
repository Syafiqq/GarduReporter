#!/usr/bin/env bash

#based on http://blog.crazybob.org/2010/02/android-trusting-ssl-certificates.html
#based on https://stackoverflow.com/questions/11117486/wrong-version-of-keystore-on-android-call/33197845#33197845

cd /home/syafiq/Documents/JetBrains/AndroidStudio/freelancer/syafiqq.freelancer.app/GarduReporter/app/src/main/res/raw
export MY_SERVER=gardureporter.000webhostapp.com

export BKS_EXT=_bks.bks
export PEM_EXT=_pem.pem

export CRT_STORE=${MY_SERVER//./_}
export PEM_STORE=/tmp/${MY_SERVER//./_}

export CLASSPATH=bcprov_jdk15on_157.jar
export CERT_PASSWORD=ez24get

declare -a arr=("" "-v1")

for i in "${arr[@]}"
do
    if [ -a ${PEM_STORE}${i}${PEM_EXT} ]; then
        rm ${PEM_STORE}${i}${PEM_EXT} || exit 1
    fi
    echo | openssl s_client -connect ${MY_SERVER}:443 2>&1 | sed -ne '/-BEGIN CERTIFICATE-/,/-END CERTIFICATE-/p' > ${PEM_STORE}${i}${PEM_EXT}
    if [ -a ${CRT_STORE}${i//-/_}${BKS_EXT} ]; then
        rm ${CRT_STORE}${i//-/_}${BKS_EXT} || exit 1
    fi
    keytool \
          -import \
          -v \
          -trustcacerts \
          -alias 0 \
          -file <(openssl x509 -in ${PEM_STORE}${i}${PEM_EXT}) \
          -keystore ${CRT_STORE}${i//-/_}${BKS_EXT} \
          -storetype BKS${i} \
          -provider org.bouncycastle.jce.provider.BouncyCastleProvider \
          -providerpath /usr/share/java/bcprov.jar \
          -storepass ${CERT_PASSWORD}
done
