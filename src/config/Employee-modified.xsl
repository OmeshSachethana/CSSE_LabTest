<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">

	<xsl:template match="employees">
		<Employees>
			<xsl:apply-templates select="employee_profile" />
		</Employees>
	</xsl:template>

	<xsl:template match="employee_profile">
		<Employee>
			<xsl:apply-templates select="employee_id" />
			<xsl:apply-templates select="employee_name" />
			<xsl:apply-templates select="address" />
			<xsl:apply-templates select="designation" />
			<xsl:apply-templates select="faculty" />
		</Employee>
	</xsl:template>

	<xsl:template match="employee_id">
		<EmployeeID>
			<xsl:value-of select="text()" />
		</EmployeeID>
	</xsl:template>

	<xsl:template match="employee_name">
		<EmployeeFullName>
			<xsl:value-of select="concat(first_name/text(),' ',last_name/text())" />
		</EmployeeFullName>
	</xsl:template>

	<xsl:template match="address">
		<EmployeeFullAddress>
			<xsl:value-of select="concat(no/text(), ',', address_line1/text(), ',', address_line2/text())" />
		</EmployeeFullAddress>
	</xsl:template>

	<xsl:template match="designation">
		<Designation>
			<xsl:value-of select="text()" />
		</Designation>
	</xsl:template>

	<xsl:template match="faculty">
		<FacultyName>
			<xsl:value-of select="@name" />
		</FacultyName>
		<Department>
			<xsl:value-of select="department/text()" />
		</Department>
	</xsl:template>

</xsl:stylesheet>
