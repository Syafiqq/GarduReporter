#!/usr/bin/env bash

#based on http://blog.crazybob.org/2010/02/android-trusting-ssl-certificates.html

cd /home/syafiq/Documents/JetBrains/AndroidStudio/freelancer/syafiqq.freelancer.app/GarduReporter/app/src/main/res/raw
export MY_SERVER=gardureporter.000webhostapp.com
echo | openssl s_client -connect ${MY_SERVER}:443 2>&1 | sed -ne '/-BEGIN CERTIFICATE-/,/-END CERTIFICATE-/p' > ${MY_SERVER}.pem