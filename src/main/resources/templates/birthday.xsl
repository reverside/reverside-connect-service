<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:ns="http://reverside.co.za/schema">
<xsl:output method="xml" omit-xml-declaration="yes"/>
<xsl:param name="channel"/>
<xsl:template match="/ns:BirthdayCommand">
<xsl:choose>
<xsl:when test="$channel='mail'">
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"></meta>
        <style>
        .preheader { display:none !important; visibility:hidden; opacity:0; color:transparent; height:0;  width:0; }
        .container { margin: 0px; background: white; }
        .head { text-align: right; padding: 10px; font-size: 2em; color: #154A9B; text-transform: lowercase;}
        .foot { text-align: center; padding: 15px; background: white; color: darkslategray; }
        .body { text-align: center; }
        .img-circle { width:100px;  height: 100px; margin: 2px; border: 2px dotted silver; border-radius: 100px; -webkit-border-radius: 100px; -moz-border-radius: 100px;text-align: center;line-height: 100px; }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="preheader">Dear Manmay, Thank you for being with us. From:</div>
            <div class="head">Re<span style="color: #BA2022;">v</span>erside</div>
            <div class="body">
                <div>
                    <img style="width: 150px; height: 100px;" src="http://www.stickpng.com/assets/images/580b585b2edbce24c47b24c6.png"/>
                </div>
                <br/>
                <div>
                    <span style="font-size: 1.5pc; font-weight: 100; color: gray;">
                    Dear Manmay,<br/>
                    <span style="color: red;">H</span><!--
                    --><span style="color: violet;">a</span><!--
                    --><span style="color: green;">p</span><!--
                    --><span style="color: orange;">p</span><!--
                    --><span style="color: blue;">y </span> <!--
                    --><span style="color:red;"> 1</span><!--
                    --><sup style="font-size: 1pc;">st </sup> <!--
                    --><span style="color: green;"> A</span><!--
                    --><span style="color:red;">n</span><!--
                    --><span style="color:pink;">n</span><!--
                    --><span style="color:blue;">i</span><!--
                    --><span style="color:red;">v</span><!--
                    --><span style="color:green;">e</span><!--
                    --><span style="color:orange;">r</span><!--
                    --><span style="color:blue;">s</span><!--
                    --><span style="color:red;">a</span><!--
                    --><span style="color:pink;">r</span><!--
                    --><span style="color:blue;">y</span>
                     </span><br/>
                    <span style="color: gray; letter-spacing: 2px;">
                        with
                    </span><br/>
                    <span style="font-size: 1.2pc; font-weight: 100; color: #154A9B;">
                        re<span style="color: #BA2022;">v</span>erside
                    </span><br/><br/>
                    <span style="color: gray; font-size: 1pc; font-style: normal; font-weight: 100; letter-spacing: 2px;">
                        Thanks for <b>All</b><br/>
                        <b>Little</b> And <b>BIG</b> Things <br/>
                        You <b>DO</b> Everyday <br/>
                        That Make Reverside <br/>
                        A <b>Great</b> Place to Work.
                    </span>
                    <br/><br/><br/>
                </div>
            </div>
            <div class="foot" style="font-size: 0.8pc;">
                <span style="letter-spacing: 2px;">Thanks</span><br/>
                <span>Reverside Team</span><br/>
            </div>
        </div>
    </body>
</html>
</xsl:when>
<xsl:when test="$channel='sms'">
Dear <xsl:value-of select="ns:name"/> Many many Happy Returns Of The Day

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