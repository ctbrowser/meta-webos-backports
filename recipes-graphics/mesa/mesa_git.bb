require ${BPN}.inc

DEFAULT_PREFERENCE = "-1"

PR = "${INC_PR}.0"
# 9.1.3 commit
SRCREV = "f32ec82a8cfcabc5b7596796f36afe7986651f02"
PV = "9.1.3+git${SRCPV}"

SRC_URI = "git://anongit.freedesktop.org/git/mesa/mesa;protocol=git \
           file://EGL-Mutate-NativeDisplayType-depending-on-config.patch \
           file://fix-glsl-cross.patch \
           file://0001-configure-Avoid-use-of-AC_CHECK_FILE-for-cross-compi.patch \
           file://0001-llvmpipe-remove-the-power-of-two-sizeof-struct-cmd_b.patch \
           "

S = "${WORKDIR}/git"

do_configure_prepend() {
    # drop .real suffix from OE flex wrapper, for details see:
    # http://lists.openembedded.org/pipermail/openembedded-core/2013-May/078279.html
    grep 'sed s/' ${S}/m4/ax_prog_flex.m4 || sed 's/ | $EGREP/ | sed "s#.real##g" | $EGREP/g' ${S}/m4/ax_prog_flex.m4 -i
}
