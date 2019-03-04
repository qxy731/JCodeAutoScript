<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${MapperEntity.parentPackage}.${MapperEntity.subPackage}.${MapperEntity.name}">

	<resultMap id="BaseResultMap" type="${BeanEntity.parentPackage}.${BeanEntity.subPackage}.${BeanEntity.name}">
	 <#list xmlEntity.properties as property>
	 	<result column="${property.columnName}" jdbcType="${property.columnType}" property="${property.propertyName}"/>
     </#list>
	</resultMap>
	<!--通用查询列-->
	<sql id = "baseSelectColunm">
		<trim prefix="" suffixOverrides=",">
		  <#list xmlEntity.properties as property>
			t.${property.columnName} AS ${property.propertyName},
		  </#list>
		</trim>
	</sql>
			
	<select id="selectByConditionSelective" resultType="${BeanEntity.parentPackage}.${BeanEntity.subPackage}.${BeanEntity.name}">
		SELECT 
		 	<include refid="baseSelectColunm"/>
		FROM ${tableName} t
		<where> 1=1 
			<#list xmlEntity.properties as property>
				<if test="condition.${property.propertyName} != null and condition.${property.propertyName} != ''"> and t.${property.columnName} = ${r'#{'}condition.${property.propertyName}} </if> 
			</#list>
		</where>
	</select>
	<!--根据主键查询-->
	<select id="selectByPrimaryKey" resultType="${BeanEntity.parentPackage}.${BeanEntity.subPackage}.${BeanEntity.name}">
		SELECT 
		 	<include refid="baseSelectColunm"/>
		FROM ${tableName} t
		WHERE	1=1
			<#list pkNames as pkName>
				and t.${pkName} = ${r'#{pkId}'}
			</#list>
	</select>
	<!--根据主键更新表字段-->
	<update id="updateByPrimaryKey" parameterType="${BeanEntity.parentPackage}.${BeanEntity.subPackage}.${BeanEntity.name}">
		UPDATE ${tableName}  
		<set>
			<#list xmlEntity.properties as property>
				${property.columnName} = ${'#{'}${property.propertyName},jdbcType=${property.columnType}},
			</#list>
		</set>
		WHERE 1= 1
			<#list pkNames as pkName>
				and ${pkName} = ${r'#{pkId}'}
			</#list>
	</update>

	<!--根据主键更新有值存在的字段-->
	<update id="updateByPrimaryKeySelective" parameterType="${BeanEntity.parentPackage}.${BeanEntity.subPackage}.${BeanEntity.name}">
		UPDATE ${tableName}  
		<set>
			<#list xmlEntity.properties as property>
				<if test="${property.propertyName} != null and ${property.propertyName} != ''"> ${property.columnName} = ${'#{'}${property.propertyName},jdbcType=${property.columnType}},</if>
			</#list>

		</set>
		WHERE 1= 1
			<#list pkNames as pkName>
				and ${pkName} = ${r'#{pkId}'}
			</#list>
	</update>
	
	<!--插入有选择的值-->
	<insert id="insertSelective" parameterType="${BeanEntity.parentPackage}.${BeanEntity.subPackage}.${BeanEntity.name}">
		INSERT INTO ${tableName}  
			<trim prefix="(" suffix=")" suffixOverrides=",">
				<#list xmlEntity.properties as property>
					<if test="${property.propertyName} != null and ${property.propertyName} != ''">
				 		${property.columnName},
				 	</if>
				 </#list>
			</trim>
		VALUES  
			<trim prefix="(" suffix=")" suffixOverrides=",">
				<#list xmlEntity.properties as property>
					<if test="${property.propertyName} != null and ${property.propertyName} != ''">
				 		${'#{'}${property.propertyName},jdbcType=${property.columnType}},
				 	</if>
				 </#list>
			</trim>
	</insert>
	<!--插入整条记录-->
	<insert id="insert" parameterType="${BeanEntity.parentPackage}.${BeanEntity.subPackage}.${BeanEntity.name}">
		INSERT INTO ${tableName}  
			<trim prefix="(" suffix=")" suffixOverrides=",">
				 
				<#list xmlEntity.properties as property>
					<if test="${property.propertyName} != null and ${property.propertyName} != ''">
				 		${property.columnName},
				 	</if>
				 </#list>
			</trim>
		VALUES  
			<trim prefix="(" suffix=")" suffixOverrides=",">
				<#list xmlEntity.properties as property>
					<if test="${property.propertyName} != null and ${property.propertyName} != ''">
				 		${'#{'}${property.propertyName},jdbcType=${property.columnType}},
				 	</if>
				 </#list>
			</trim>
	</insert>
	<!--根据主键删除-->
	<delete id="deleteByPrimaryKey" parameterType="java.lang.String">
		DELETE FROM ${tableName} 
		WHERE 1 = 1
			<#list pkNames as pkName>
				and ${pkName} = ${r'#{pkId}'}
			</#list>
	</delete>
</mapper>