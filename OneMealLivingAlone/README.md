One Meal Living Alone
=====

## Build
시스템에 Maven이 설치되어 있다면 `maven-build.sh`를 실행하고, Ecplise IDE에서 하려면 아래 goal들을 이용해 Maven build를 수행하세요.
```
clean:clean compiler:compile jar:jar dependency:copy-dependencies antrun:run@copy-files
```


## Run in Windows
Maven build가 끝나면, `run-omla.bat` 파일을 실행하세요. 코드 페이지가 **MS949**인 command-line에서 작동합니다.


## Deployment
`target` 디렉터리에서 `data`, `dependency`, `log4j2.xml`, `one-meal-living-alone-*.jar`, `run-omla.bat`을 배포하세요.
