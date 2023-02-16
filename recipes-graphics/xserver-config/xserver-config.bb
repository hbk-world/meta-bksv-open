LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

inherit sca

SRC_URI_append = "file://session \
				  file://xserver-common \
                  file://index.theme \
"

FILES_${PN} = "${sysconfdir}/X11/xserver-common \
               ${sysconfdir}/mini_x/session \
               ${datadir}/icons/default/index.theme \
                "

do_install_append() {
	install -d ${D}/${sysconfdir}
	install -d ${D}/${sysconfdir}/X11
	install -m 0755 ${WORKDIR}/xserver-common ${D}${sysconfdir}/X11/xserver-common

    install -d ${D}/${sysconfdir}
    install -d ${D}/${sysconfdir}/mini_x
    install -m 0755 ${WORKDIR}/session ${D}${sysconfdir}/mini_x/session
    
    install -d ${D}${datadir}/icons/default
    install -m 0755 ${WORKDIR}/index.theme ${D}${datadir}/icons/default/index.theme
}

do_deploy () {
   install -d ${DEPLOY_DIR_IMAGE}
   install -m 0755 ${WORKDIR}/session ${DEPLOY_DIR_IMAGE}/session
}
addtask deploy after do_install
