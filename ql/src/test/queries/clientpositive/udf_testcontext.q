-- Test to see if the UDFContext is properly initialized on the backend
-- and if a UDF can obtain the JobConf using the UDFContext.

CREATE TEMPORARY FUNCTION getconfprop AS 'org.apache.hadoop.hive.ql.udf.UDFGetConfProp';

set test.udfcontext.prop="Can the UDF see this on the backend?";

select getconfprop('test.udfcontext.prop') FROM src LIMIT 10;

DROP TEMPORARY FUNCTION getconfprop;
