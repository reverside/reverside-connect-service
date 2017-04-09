<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:ns="http://reverside.co.za/schema">
<xsl:output method="text" media-type="text/plain" indent="no"/>
<xsl:template match="/ns:quotation">Dear <xsl:value-of select="concat(ns:firstName, ' ', ns:lastName)"/>,

How are you ?
Hope you are doing well.

Thanks
Reverside Team
</xsl:template>
</xsl:stylesheet>