@echo on

@rem proto.exe路径
set protoExePath=E:\Work\game\
@rem ProtoBuf工程路径
set protoBasePath=E:\Work\game\protobuf-protocol\
@rem .proto文件路径
set protoFilePath=%protoBasePath%src\main\java\com\game\
@rem Java生成路径
set javaOutPath=%protoBasePath%src\main\java 

for /r %protoFilePath% %%i in (*.proto) do (
	%protoExePath%protoc.exe --proto_path=%protoFilePath% --java_out=%javaOutPath% %%i
)

pause