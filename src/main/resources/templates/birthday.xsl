<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:ns="http://reverside.co.za/schema">
<xsl:output method="text" media-type="text/plain" indent="no"/>
<xsl:template match="/ns:birthday">
<xsl:param name="$mode"/>
Dear <xsl:value-of select="concat(ns:firstName, ' ', ns:lastName)"/>,

Wish You A Happy Birthday :-)

Thanks
Reverside Team
</xsl:template>

</xsl:stylesheet>