<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet
        xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
        version="3.0"
>
    <xsl:output method="html" indent="yes" media-type="text/html" encoding="UTF-8"/>
    <xsl:template match="/">
        <html>
            <head>
                <title>Orders</title>
            </head>
            <body>
                <xsl:apply-templates/>
            </body>
            <style>
                table{
                border: 1px solid black;
                }
                th{
                border: 1px black;
                padding: 5;
                margin: 5;
                }
                td {
                border: 1px solid black;
                padding: 5;
                margin: 5;
                }
            </style>
        </html>
    </xsl:template>
    <xsl:template match="OrderDTO">
        <h2>Orders</h2>
        <p>ID: <xsl:value-of select="id"/></p>
        <p>Customer ID: <xsl:value-of select="customerId"/></p>
        <p>Number: <xsl:value-of select="number"/></p>
        <p>Status: <xsl:value-of select="status"/></p>
        <table>
            <tr>
                <th>Product ID</th>
                <th>Quantity</th>
            </tr>
            <xsl:for-each select="content">
                <xsl:for-each select="content">
                    <tr>
                        <td>
                            <xsl:value-of select="productId"/>
                        </td>
                        <td>
                            <xsl:value-of select="quantity"/>
                        </td>
                    </tr>
                </xsl:for-each>
            </xsl:for-each>
        </table>
    </xsl:template>
</xsl:stylesheet>