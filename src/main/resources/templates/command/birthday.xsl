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
    <head>
        <style>
            .photo {
                width:100px;
                height: 100px;
                margin: 2px;
                border: 2px dotted silver;
                border-radius: 100px;
                -webkit-border-radius: 100px;
                -moz-border-radius: 100px;
                text-align: center;
                line-height: 100px;
            }
            .block-center {
               padding: 10px;
               text-align: center;
            }
        </style>
    </head>
<body>
    <div class="block-center">
        <img alt="Loading...." class="photo">
            <xsl:attribute name="src" select="ns:photo"/>
        </img>
    </div>
    <div class="block-center">
        <div>
            Dear <xsl:value-of select="ns:name"/>
            <br/>
            <br/>
        </div>
        <div style="font-size: 2pc; font-weight: 900;">
            <span style="color: red;">H</span>
            <span style="color: violet;">a</span>
            <span style="color: green;">p</span>
            <span style="color: orange;">p</span>
            <span style="color: blue;">y</span>
            <span style="color: green;"> B</span>
            <span style="color: red;">i</span>
            <span style="color: violet;">r</span>
            <span style="color: orange;">t</span>
            <span style="color: blue;">h</span>
            <span style="color: red;"> D</span>
            <span style="color: violet;">a</span>
            <span style="color: green;">y</span>
        </div>
        <div style="font-size: 1pc; color: gray;">
            <br/>
            May all your dreams and wishes come true...
            <br/>
            <br/>
            Enjoy Your Day
        </div>
    </div>

    <div class="block-center" >
        <div style="letter-spacing: 2px; font-size: 0.6pc;">
            CHEERS
        </div>
        <div style="font-size: 1pc;">
            <span style="color: blue;">re</span>
            <span style="color: red;">v</span>
            <span style="color:blue;">erside</span>
        </div>
        <div>

        </div>
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