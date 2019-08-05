#!/bin/bash
export RANDFILE=/tmp/.rnd
openssl genrsa -out /tmp/self-signed.key 2048

openssl rsa -in /tmp/self-signed.key -out /tmp/self-signed.key.insecure
# shuffle the key names to continue without passphrases
mv /tmp/self-signed.key /tmp/self-signed.key.secure
mv /tmp/self-signed.key.insecure /tmp/self-signed.key

openssl req -new -key /tmp/self-signed.key -config $1 -out /tmp/self-signed.csr
openssl x509 -req -days 365 -in /tmp/self-signed.csr -signkey /tmp/self-signed.key -out /tmp/self-signed.crt

mv /tmp/self-signed.crt /etc/ssl/certs/self-signed.crt
mv /tmp/self-signed.key /etc/ssl/private/self-signed.key
