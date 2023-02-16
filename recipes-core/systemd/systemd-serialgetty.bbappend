
do_install_append() {
    # Remove the symlink that starts getty on the serial port.
    # A systemd 'generator' will ensure serial getty is started if and only
    # if a "console=..." parameter was specified on the kernel command line
    rm ${D}${sysconfdir}/systemd/system/getty.target.wants/serial-getty\@ttyS0.service
}
