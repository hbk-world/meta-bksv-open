
hostname="sxu"

do_install_append () {

    # modify /etc/fstab to mount root with noatime
    sed -i "s/^\/dev\/root.*/\/dev\/root            \/                    auto       defaults,noatime      1  1/" ${D}${sysconfdir}/fstab

    cat >> ${D}${sysconfdir}/fstab <<EOF

/dev/mmcblk1p1       /mnt/data            auto       defaults,noatime      0  0
/dev/mmcblk2p3       /mnt/bksv            auto       defaults,noatime      0  0
/dev/mmcblk2p7       /mnt/config          auto       ro,noatime            0  0
EOF

}
