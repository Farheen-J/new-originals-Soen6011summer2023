import React, { useState } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import { Grid, Box, CssBaseline, Paper, Typography } from "@material-ui/core";
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from 'chart.js';
import { Doughnut } from 'react-chartjs-2';

const useStyles = makeStyles(theme => ({
    root: {
        display: 'flex',
        flexDirection: 'row', // Arrange components side by side
    },
    paper: {
        padding: theme.spacing(3),
        marginTop: theme.spacing(10),
        display: 'flex',
        flexDirection: 'column',
        backgroundColor: "#fafafaeb"
        //alignItems: 'center',
    },
    countContainer: {
        display: 'flex',
        justifyContent: 'space-between',
        // alignItems: 'center',
        marginTop: theme.spacing(30),
    },
    chartContainer: {
        display: 'flex',
        justifyContent: 'left',
        width: "300px",
        height: "300px",
        // alignItems: 'center',
        margin: theme.spacing(2),
    },
    componentContainer: {
        margin: theme.spacing(2), // Add margin between components
    },
}));

const chartOptions = {
    responsive: true, // Enable responsiveness
    maintainAspectRatio: false, // Set to false if you want the chart to scale based on container size
    legend: {
        display: true,
        position: 'right', // or 'bottom', 'top', 'left'
    },
};


function JobCount({ postedJobs, activeJobs, inactiveJobs, classes }) {
    const jobData = {
        labels: ['Posted Jobs', 'Active Jobs', 'Inactive Jobs'],
        datasets: [
            {
                data: [postedJobs, activeJobs, inactiveJobs],
                backgroundColor: ['#FF6384', '#36A2EB', '#FFCE56'],
                hoverBackgroundColor: ['#FF6384', '#36A2EB', '#FFCE56'],
                hoverOffset: 4
            },
        ],
    };

    return (
        <div className={classes.componentContainer}>
            <Paper className={classes.paper}>
                <Typography variant="h5">Job Count</Typography>
                <div className={classes.chartContainer}>
                    <Doughnut data={jobData} options={chartOptions} />
                </div>
            </Paper>
        </div>
    );
}

function UserCount({ candidateCount, employerCount, classes }) {
    const userData = {
        labels: ['Candidate Count', 'Employer Count'],
        datasets: [
            {
                data: [candidateCount, employerCount],
                backgroundColor: ['#FF6384', '#36A2EB'],
                hoverBackgroundColor: ['#FF6384', '#36A2EB'],
                hoverOffset: 4
            },
        ],
    };

    return (
        <div className={classes.componentContainer}>
            <Paper className={classes.paper}>
                <Typography variant="h5">User Count</Typography>
                <div className={classes.chartContainer}>
                    <Doughnut data={userData} options={chartOptions} />
                </div>
            </Paper>
        </div>
    );
}

function Tracking({ data }) {
    const classes = useStyles();
      // Register the necessary Chart.js components
  ChartJS.register(ArcElement, Tooltip, Legend);

    const { posted_jobs, active_jobs, inactive_jobs } = data.data.jobs_count;
    const { candidate_count, employer_count } = data.data.users_count;

    return (
        <div className={classes.centerContainer}>
            <Grid>
                <Box className={classes.root}>
                    <JobCount
                        postedJobs={posted_jobs}
                        activeJobs={active_jobs}
                        inactiveJobs={inactive_jobs}
                        classes={classes} // Pass the classes prop
                    />
                    <UserCount
                        candidateCount={candidate_count}
                        employerCount={employer_count}
                        classes={classes} // Pass the classes prop
                    />
                </Box>

            </Grid>
        </div>
    );
}

export default Tracking;