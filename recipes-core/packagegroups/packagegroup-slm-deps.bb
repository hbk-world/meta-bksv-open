# Copyright (C) 2014, 2015 O.S. Systems Software LTDA.

SUMMARY = "Dependencies required by SLM application software"
LICENSE = "CLOSED"

inherit packagegroup
inherit cmake_qt5

PACKAGES = " \
    ${PN} \
	${PN}-libWebXi \
	${PN}-slm \
	${PN}-soundencoding \
	${PN}-webxiserver \
"

RDEPENDS_${PN}-libWebXi = " \
    zmqpp \
    tzdata \
    libgcc \
    libstdc++ \
    boost \
    curl \
"

RDEPENDS_${PN}-slm = " \
    qtbase \
    iw \
    libcurl \
    ttf-tahoma-wine \
    networkmanager \
    networkmanager-qt \
    smbclient \
    xserver-config \
	tzdata tzdata-misc tzdata-posix tzdata-right tzdata-africa \
    tzdata-americas tzdata-antarctica tzdata-arctic tzdata-asia \
    tzdata-atlantic tzdata-australia tzdata-europe tzdata-pacific \
    adwaita-icon-theme-cursors \
    noto-fonts-medium \
    noto-fonts-regular \
    watchdog \
    coreutils \
"

RDEPENDS_${PN}-soundencoding = " \
    libmp3lame \
    libflac \
    flac \
    libflac++ \
    libflac \
    lame \
"

RDEPENDS_${PN}-webxiserver = " \
    nginx \
"

RDEPENDS_${PN} = " \
    ${RDEPENDS_${PN}-libWebXi} \
    ${RDEPENDS_${PN}-slm} \
    ${RDEPENDS_${PN}-soundencoding} \
    ${RDEPENDS_${PN}-webxiserver} \
"
