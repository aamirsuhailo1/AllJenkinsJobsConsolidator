![AllJenkinsJobsConsolidator](https://user-images.githubusercontent.com/8610673/120975502-ebc87b00-c78e-11eb-9d4f-7f047e717e0d.png)

## Problem statement:
Every company and their respective teams have lot of jenkins job running everyday. these jenkins jobs can contain smoke tests, Integration tests, sanity tests etc.these jobs are maintained invidually.for each job, if email is configured, a new email will get triggered after test execution.it takes lot of time to go through each and every job email and then analyse the cause of failure and success.To overcome this problem, I have developed this utility.

## Solution:
This utility will solve the above problem. It will fetch the job details from each and every jenkins job and it consolidates all the details and it sends a single email with a table containing details of all the jenkins job. instead of n number of emails for n jenkins job, only one email will get triggered.

This is generic utility where each and every company or any team or any individual can use it. it will saves lot of time.


## How To Run It:
1. Clone this repo
2. In the config.properties file, give all the jenkins jobs urls for which you want to include in the report and add the details related to your email
4. In the app-password, go to your email settings and find apppassword and use that.
5. Run Runner.java file


## Output:
please refer to the image(AllJenkinsJobsConsolidator.png) attached in the project files
