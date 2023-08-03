import React, { useState } from "react";
import { makeStyles } from "@material-ui/core/styles";
import { Grid, Paper, Typography, Box, Grow, Dialog, DialogTitle, DialogContent } from "@material-ui/core";
import { createTheme, ThemeProvider } from "@material-ui/core/styles";
import JobDescription from "../JobListings/JobDescription";
import JobOfferDetails from "../Employer/JobOfferDetails";

const useStyles = makeStyles((theme) => ({
    // Define the styles for the component using makeStyles hook
    root: {
        flexGrow: 1,
        maxWidth: 1000,
        margin: "0 auto",
        marginTop: 40,
        fontFamily: "Arial, sans-serif",
    },
    kanbanBoard: {
        display: "flex",
        flexWrap: "wrap",
        marginTop: "70px",
        justifyContent: "flex-start",
    },
    column: {
        flex: "1 1 25%",
        padding: 16,
        textAlign: "center",
        overflowY: "auto",
        minHeight: 300,
        borderRadius: 4,
        backgroundColor: "#fffbf6f0",
        boxShadow: theme.shadows[2],
        transition: "background-color 0.2s ease",
        "&:hover": {
            backgroundColor: "#f0f0f0",
        },
        marginLeft: theme.spacing(1),
        marginRight: theme.spacing(1),
    },
    appliedColumn: {
        marginLeft: theme.spacing(-15),
    },
    companyName: {
        fontWeight: 900,
        color: "#0540c5", 
    },
    position: {
        color: "#000000",
    },
    card: {
        padding: 16,
        marginBottom: 16,
        borderRadius: 4,
        backgroundColor: "#fff",
        boxShadow: theme.shadows[2],
        cursor: "pointer",
        transition: "box-shadow 0.2s ease",
        "&:hover": {
            boxShadow: theme.shadows[4],
        },
    },
    statusHeader: {
        marginBottom: 16,
        fontWeight: "bold",
        borderBottom: "1px solid #ddd",
        paddingBottom: 8,
    },
}));

const font = "League Spartan, monospace";
const theme = createTheme({
    // Define the theme for Material-UI components
    typography: {
        fontFamily: font,
        button: {
            textTransform: "none",
        },
    },
});

const TrackApplications = (props) => {
    const classes = useStyles();

    const [isDialogOpen, setIsDialogOpen] = useState(false);
    const [jobOffer, setJobOffer] = useState(false);
    const [selectedCardData, setSelectedCardData] = useState(null);

    const handleDialogOpen = (data) => {
        setIsDialogOpen(true);
        setSelectedCardData(data);
    };

    const handleDialogClose = () => {
        setIsDialogOpen(false);
        setSelectedCardData(null);
    };

    const handleJobOffer = (data) => {
        setJobOffer(true);
        setSelectedCardData(data);
    };

    const handleJobOfferClose = () => {
        setJobOffer(false);
        setSelectedCardData(null);
    };

    const [applications, setApplications] = useState({
        posted: [
            {
                "id": 1,
                "company": "Photosnap",
                "position": "Senior Frontend Developer",
                "role": "Frontend",
                "level": "Senior",
                "posted_at": "1d ago",
                "contract": "Full Time",
                "location": "USA Only",
                "languages": ["HTML", "CSS", "JavaScript"],
                "tools": [],
                "employer_email": "employer@gmail.com",
                "description": "Description….Roles and responsibility",
                "requirements": "degree..experience..etc"
            },
            {
                "id": 2,
                "company": "Manage",
                "position": "Fullstack Developer",
                "role": "Fullstack",
                "level": "Midweight",
                "posted_at": "1d ago",
                "contract": "Part Time",
                "location": "Remote",
                "languages": ["Python"],
                "tools": ["React"],
                "employer_email": "employer@gmail.com",
                "description": "Description….Roles and responsibility",
                "requirements": "degree..experience..etc"
            },
        ],
        rejected: [
            {
                "id": 3,
                "company": "Account",
                "position": "Junior Frontend Developer",
                "role": "Frontend",
                "level": "Junior",
                "posted_at": "2d ago",
                "contract": "Part Time",
                "location": "USA Only",
                "languages": ["JavaScript"],
                "tools": ["React", "Sass"],
                "employer_email": "employer@gmail.com",
                "description": "Description….Roles and responsibility",
                "requirements": "degree..experience..etc"
            },
            {
                "id": 4,
                "company": "MyHome",
                "position": "Junior Frontend Developer",
                "role": "Frontend",
                "level": "Junior",
                "posted_at": "5d ago",
                "contract": "Contract",
                "location": "USA Only",
                "languages": ["CSS", "JavaScript"],
                "tools": [],
                "employer_email": "employer@gmail.com",
                "description": "Description….Roles and responsibility",
                "requirements": "degree..experience..etc"
            },
        ],
        interview: [
            {
                "id": 5,
                "company": "Loop Studios",
                "position": "Software Engineer",
                "role": "FullStack",
                "level": "Midweight",
                "posted_at": "1w ago",
                "contract": "Full Time",
                "location": "Worldwide",
                "languages": ["JavaScript"],
                "tools": ["Ruby", "Sass"],
                "employer_email": "employer@gmail.com",
                "description": "Description….Roles and responsibility",
                "requirements": "degree..experience..etc"
            },
            {
                "id": 6,
                "company": "FaceIt",
                "position": "Junior Backend Developer",
                "role": "Backend",
                "level": "Junior",
                "posted_at": "2w ago",
                "contract": "Full Time",
                "location": "UK Only",
                "languages": ["Ruby"],
                "tools": ["RoR"],
                "employer_email": "employer@gmail.com",
                "description": "Description….Roles and responsibility",
                "requirements": "degree..experience..etc"
            },
        ],
        job_offers: [
            {
                "id": 7,
                "company": "Shortly",
                "position": "Junior Developer",
                "role": "Frontend",
                "level": "Junior",
                "posted_at": "2w ago",
                "contract": "Full Time",
                "location": "Worldwide",
                "languages": ["HTML", "JavaScript"],
                "tools": ["Sass"],
                "employer_email": "employer@gmail.com",
                "description": "Description….Roles and responsibility",
                "requirements": "degree..experience..etc"
            },
        ],
    });

    return (
        <ThemeProvider theme={theme}>
            <div className={classes.root}>
                <Box className={classes.kanbanBoard}>
                    <div className={`${classes.column} ${classes.appliedColumn}`}>
                        <Typography variant="h6" className={classes.statusHeader}>
                            Posted
                        </Typography>
                        {applications.posted.map((app) => (
                            <Grow in key={app.company}>
                                <Paper
                                    className={classes.card}
                                    onClick={() => handleDialogOpen(app)}
                                >
                                    <Typography variant="subtitle1" className={classes.companyName}>{app.company}</Typography>
                                    <Typography variant="body2"  className={classes.position}>{app.position}</Typography>
                                </Paper>
                            </Grow>
                        ))}
                    </div>
                    <div className={classes.column}>
                        <Typography variant="h6" className={classes.statusHeader}>
                            Interview
                        </Typography>
                        {applications.interview.map((app) => (
                            <Grow in key={app.company}>
                                <Paper
                                    className={classes.card}
                                    onClick={() => handleDialogOpen(app)}
                                >
                                    <Typography variant="subtitle1" className={classes.companyName}>{app.company}</Typography>
                                    <Typography variant="body2"  className={classes.position}>{app.position}</Typography>
                                </Paper>
                            </Grow>
                        ))}
                    </div>
                    <div className={classes.column}>
                        <Typography variant="h6" className={classes.statusHeader}>
                            Job Offers
                        </Typography>
                        {applications.job_offers.map((app) => (
                            <Grow in key={app.company}>
                                <Paper
                                    className={classes.card}
                                    onClick={() => handleJobOffer(app)}
                                >
                                    <Typography variant="subtitle1" className={classes.companyName}>{app.company}</Typography>
                                    <Typography variant="body2"  className={classes.position}>{app.position}</Typography>
                                </Paper>
                            </Grow>
                        ))}
                    </div>
                    <div className={classes.column}>
                        <Typography variant="h6" className={classes.statusHeader}>
                            Rejected
                        </Typography>
                        {applications.rejected.map((app) => (
                            <Grow in key={app.company}>
                                <Paper
                                    className={classes.card}
                                    onClick={() => handleDialogOpen(app)}
                                >
                                    <Typography variant="subtitle1" className={classes.companyName}>{app.company}</Typography>
                                    <Typography variant="body2"  className={classes.position}>{app.position}</Typography>
                                </Paper>
                            </Grow>
                        ))}
                    </div>
                </Box>
                <Dialog open={isDialogOpen} onClose={handleDialogClose}>
                    <DialogTitle>Job Description</DialogTitle>
                    <DialogContent>
                        {selectedCardData &&
                            (
                                <JobDescription
                                    open={isDialogOpen}
                                    handleClose={handleDialogClose}
                                    data={selectedCardData}
                                    invoker="track"
                                    candidateData={JSON.parse(sessionStorage.getItem("AUTH_TOKEN"))}
                                />
                            )
                        }
                    </DialogContent>
                </Dialog>
                {selectedCardData && ( 
                    <Dialog open={jobOffer} onClose={handleJobOfferClose}>
                        <JobOfferDetails
                            open={jobOffer}
                            handleClose={handleJobOfferClose}
                            data={selectedCardData}
                        />
                    </Dialog>
                )}
            </div>
        </ThemeProvider>
    );
};

export default TrackApplications;
