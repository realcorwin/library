package dik.library.shell;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class BatchCommands {

    private final JobLauncher jobLauncher;
    private final Job migrationJob;

    public BatchCommands(JobLauncher jobLauncher, Job migrationJob) {
        this.jobLauncher = jobLauncher;
        this.migrationJob = migrationJob;
    }

    @ShellMethod("Migrate data from MongoDB to H2")
    public void migrate() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        jobLauncher.run(migrationJob, new JobParametersBuilder().toJobParameters());
    }
}
