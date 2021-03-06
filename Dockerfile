# Stage that builds the application, a prerequisite for the running stage
FROM maven:3-jdk-11 as build

# Stop running as root at this point
RUN useradd -m myuser
WORKDIR /usr/src/app/
RUN chown myuser:myuser /usr/src/app/
USER myuser

# Copy pom.xml and prefetch dependencies so a repeated build can continue from the next step with existing dependencies
COPY --chown=myuser pom.xml ./

# Copy all needed project files to a folder
COPY --chown=myuser:myuser src src

# Build the production package, assuming that we validated the version before so no need for running tests again
RUN mvn clean package -DskipTests 

# Running stage: the part that is used for running the application
FROM openjdk:11
COPY --from=build /usr/src/app/target/*.jar /usr/app/app.jar
RUN useradd -m myuser
USER myuser
EXPOSE 8080
CMD java -jar /usr/app/app.jar