FROM openjdk:openjdk:14.0.1-oraclelinux7
VOLUME /tmp
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
CMD ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-jar", "java-and-docker.jar", "-Xmx128m"]
ENTRYPOINT ["java","-cp","app:app/lib/*","com.cmuhatia.icube.question.three.IouTrackerApplication"]
