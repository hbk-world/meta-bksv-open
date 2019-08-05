#!/bin/sh
if [ -f /mnt/config/hostname ]; then
        echo "copy hostname BK--\n"
        hostname --file /mnt/config/hostname
        cp /mnt/config/hostname /etc/hostname
else
        echo "no copy of hostname\n"
fi

