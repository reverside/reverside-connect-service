<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:ns="http://reverside.co.za/schema">

    <xsl:output method="text"  omit-xml-declaration="yes" />
    
    <xsl:param name="channel"/>

<xsl:template match="/ns:BirthdayCommand">
<xsl:choose>

<xsl:when test="$channel='mail'">
<html>
<body>
<div style="width:100%; text-align: center;">
<img alt="Loading...." style="width:100px;  height: 100px;  margin: 2px; border: 2px dotted silver; border-radius: 100px; -webkit-border-radius: 100px; -moz-border-radius: 100px; text-align: center; line-height: 100px;">
    <xsl:attribute name="src" select="ns:photo"/>
</img>
</div>
<div style="width:100%; text-align: center;">
Dear <xsl:value-of select="ns:name"/>
<h1>
<span style="color: red;">H</span><span style="color: violet;">a</span><span style="color: green;">p</span><span style="color: orange;">p</span><span style="color: blue;">y</span> &#160;<span style="color: green;">B</span><span style="color: red;">i</span><span style="color: violet;">r</span><span style="color: orange;">t</span><span style="color: blue;">h</span> &#160;<span style="color: red;">D</span><span style="color: violet;">a</span><span style="color: green;">y</span>
</h1>
<div>
Enjoy Your Day 
</div>
-Reverside
</div>
</body>
</html>
</xsl:when>

<xsl:when test="$channel='sms'">
Good Morning :-) <xsl:value-of select="ns:name"/>

       _!_!_!_!_
     !..............!
   !..................!
 !______________!
 Happy Birthday

From:
Reverside
</xsl:when>

</xsl:choose>
</xsl:template>

</xsl:stylesheet>