#!/bin/bash

folder=/mnt/bksv/system-connections

if [ -d /mnt/bksv ]; then
    if [ ! -d $folder ]; then
        echo "Creating $folder"
        mkdir -v $folder
    fi
fi
