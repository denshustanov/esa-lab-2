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
    <xsl:template match="PageDTO">
        <h2>Orders</h2>
        <p>Page
            <xsl:value-of select="number+1"/> of
            <xsl:value-of select="totalPages"/>
        </p>
        <p>Page size:
            <xsl:value-of select="size"/>
        </p>
        <p>Total count:
            <xsl:value-of select="totalCount"/>
        </p>
        <xsl:if test="number > 0">
            <xsl:element name="a">
                <xsl:attribute name="href">
                    <xsl:value-of select="concat('orders?page=', number - 1)"/>
                </xsl:attribute>
                Previous page
            </xsl:element>
        </xsl:if>
        <xsl:if test="( totalPages - 1) > number">
        <xsl:element name="a">
            <xsl:attribute name="href">
                <xsl:value-of select="concat('orders?page=', number + 1)"/>
            </xsl:attribute>
            Next page
        </xsl:element>
        </xsl:if>
        <table>
            <tr>
                <th>Number</th>
                <th>Customer ID</th>
                <th>Status</th>
            </tr>
            <xsl:for-each select="items">
                <xsl:for-each select="items">
                    <tr>
                        <td>
                            <xsl:variable name="orderId" select="id"/>
                            <xsl:element name="a">
                                <xsl:attribute name="href">
                                    <xsl:value-of select="concat('orders/', $orderId)"/>
                                </xsl:attribute>
                                <xsl:value-of select="number"/>
                            </xsl:element>
                        </td>
                        <td>
                            <xsl:value-of select="customerId"/>
                        </td>
                        <td>
                            <xsl:value-of select="status"/>
                        </td>
                    </tr>
                </xsl:for-each>
            </xsl:for-each>
        </table>
    </xsl:template>
</xsl:stylesheet>