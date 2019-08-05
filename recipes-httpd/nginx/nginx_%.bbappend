FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += " \
    file://front.html \
    file://index.html \
"

FILES_${PN} += " \
    ${NGINX_WWWDIR}/html/front.html \
    ${NGINX_WWWDIR}/html/index.html \
    ${NGINX_WWWDIR}/html/serial_number \
    ${NGINX_WWWDIR}/html/hardware_version \
"

do_install_append () {
    install -d ${D}${NGINX_WWWDIR}
    install -d ${D}${NGINX_WWWDIR}/html
    install -m 0644 ${WORKDIR}/front.html ${D}${NGINX_WWWDIR}/html/
    install -m 0644 ${WORKDIR}/index.html ${D}${NGINX_WWWDIR}/html/

    ln -s /usr/share/fonts/truetype/SinewsSansPro-Regular.ttf ${D}${NGINX_WWWDIR}/html/SinewsSansPro-Regular.ttf

    ln -s /mnt/config/serial_number ${D}${NGINX_WWWDIR}/html/serial_number
    ln -s /mnt/config/hardware_version ${D}${NGINX_WWWDIR}/html/hardware_version
}
