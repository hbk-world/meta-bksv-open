
hostname="sxu"

do_install_append () {

    cat >> ${D}${sysconfdir}/fstab <<EOF

/dev/mmcblk1p1       /mnt/data            auto       defaults              0  0
/dev/mmcblk2p3       /mnt/bksv            auto       defaults              0  0
/dev/mmcblk2p7       /mnt/config          auto       ro                    0  0
EOF

}
