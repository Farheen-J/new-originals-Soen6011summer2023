import { useContext, useEffect, useState } from "react";
import { Formik, Form, Field,ErrorMessage } from "formik";
import * as Yup from "yup";
import {
  Button,
  Grid,
  Typography,
  Modal,
  Paper,
  makeStyles,
  TextField,
  MenuItem,
} from "@material-ui/core";
import axios from "axios";
import ChipInput from "material-ui-chip-input";
import moment from 'moment';
//import MyJobs from "./MyJobs";


//import { SetPopupContext } from "../../App";

import apiList from "../../config/constant";

const useStyles = makeStyles((theme) => ({
  body: {
    height: "inherit",
  },
  popupDialog: {
    height: "100%",
    display: "flex",
    alignItems: "center",
    justifyContent: "center",
    // padding: "30px",
  },
}));

const validationSchema = Yup.object().shape({
  company: Yup.string().required("Company name is required"),
  employer_email: Yup.string().email("Invalid email").required("Email is required"),
  position: Yup.string().required("Position is required"),
  role: Yup.string().required("Role is required"),
  level: Yup.string().required("Level is required"),
  description: Yup.string().required("Description is required"),
  requirements: Yup.string().required("Requirements are required"),
  location: Yup.string().required("Location is required"),
  salary: Yup.number().min(0, "Salary must be a positive number").required("Salary is required"),
});

const CreateJobs = (props) => {
  const classes = useStyles();
  const initialValues = {
      company: "",
      employer_email: "",
      //logo,
      position: "",
      role: "",
      level: "",
      posted_at: moment().format(),
      contract: "Full Time",
      location: "",
      tools: [],
      languages: [],
      description: "",
      requirements: "",
      salary: 0,
    };
//  const setPopup = useContext(SetPopupContext);
  const [success, setSuccess] = useState(false);
  const [jobDetails, setJobDetails] = useState({
    company: "",
    employer_email: "",
    //logo,
    position: "",
    role: "",
    level: "",
    posted_at: moment().format() ,
    contract: "Full Time",
    location: "",
    tools: [],
    languages: [],
    description: "",
    requirements: "",
    salary: 0,
  });

  const handleInput = (key, value) => {
    setJobDetails({
      ...jobDetails,
      [key]: value,
    });
  };

  const handleUpdate = (values) => {
    console.log(values);
    axios
      .post(apiList.jobs, values, {
              headers: {
                Authorization: `Bearer ${localStorage.getItem("token")}`,
              },
            })
      .then((response) => {
//        setPopup({
//          open: true,
//          severity: "success",
//          message: response.data.message,
//        });
        setSuccess(true);
        setJobDetails({
          company: "",
          employer_email: "",
          position: "",
          role: "",
          level: "",
          posted_at: moment().format(),
          contract: "Full Time",
          location: "",
          tools: [],
          languages: [],
          description: "",
          requirements: "",
          salary: 0,
        });
      })
      .catch((err) => {
//        setPopup({
//          open: true,
//          severity: "error",
//          message: err.response.data.message,
//        });
        console.log(err.response);
      });
      setSuccess(true);
  };

  return (
    <>
      <Grid
        container
        item
        direction="column"
        alignItems="center"
        style={{ padding: "50px", minHeight: "93vh", width: "", paddingRight: "190px" }}
      >
        <Grid item>
          <Typography variant="h2">Add Job</Typography>
        </Grid>

        <Grid item container xs direction="column" justify="center">
          <Grid item>
            <Paper
//              style={{
//                padding: "20px",
//                outline: "none",
//                display: "flex",
//                flexDirection: "column",
//                justifyContent: "center",
//                alignItems: "center",
//              }}
            >
            <Formik
                            initialValues={initialValues}
                            validationSchema={validationSchema}
                            onSubmit={(values, { setSubmitting }) => {
                              handleUpdate(values);
                              setSubmitting(false);
                            }}
                          >
                            {({ isSubmitting }) => (
              <Form>
              <Grid
                container
                direction="column"
                alignItems="stretch"
                spacing={3}
              >
                <Grid
                                      container
                                      direction="column"
                                      alignItems="stretch"
                                      spacing={3}
                                    >
                                      <Grid item>
                                        <Field
                                          as={TextField}
                                          name="company"
                                          label="Company"
                                          variant="outlined"
                                          fullWidth
                                        />
                                        <ErrorMessage name="company" component="div" />
                                      </Grid>
                                      <Grid item>
                                        <Field
                                          as={TextField}
                                          name="employer_email"
                                          label="Employer email"
                                          type="email"
                                          variant="outlined"
                                          fullWidth
                                        />
                                        <ErrorMessage name="employer_email" component="div" />
                                      </Grid>
                                      <Grid item>
                                        <Field
                                          as={TextField}
                                          name="position"
                                          label="Position"
                                          variant="outlined"
                                          fullWidth
                                        />
                                        <ErrorMessage name="position" component="div" />
                                      </Grid>
                                      <Grid item>
                                        <Field
                                          as={TextField}
                                          name="role"
                                          label="Role"
                                          variant="outlined"
                                          fullWidth
                                        />
                                        <ErrorMessage name="role" component="div" />
                                      </Grid>
                                      <Grid item>
                                        <Field
                                          as={TextField}
                                          name="level"
                                          label="Level"
                                          variant="outlined"
                                          fullWidth
                                        />
                                        <ErrorMessage name="level" component="div" />
                                      </Grid>
                                      <Grid item>
                                        <Field
                                          as={ChipInput}
                                          className={classes.inputBox}
                                          label="Tools"
                                          variant="outlined"
                                          helperText="Press enter to add tools"
                                          name="tools"
                                          fullWidth
                                        />
                                      </Grid>
                                      <Grid item>
                                        <Field
                                          as={ChipInput}
                                          className={classes.inputBox}
                                          label="Languages"
                                          variant="outlined"
                                          helperText="Press enter to add Languages"
                                          name="languages"
                                          fullWidth
                                        />
                                      </Grid>
                                      <Grid item>
                                        <Field
                                          as={TextField}
                                          name="description"
                                          label="Description"
                                          variant="outlined"
                                          fullWidth
                                        />
                                        <ErrorMessage name="description" component="div" />
                                      </Grid>
                                      <Grid item>
                                        <Field
                                          as={TextField}
                                          name="requirements"
                                          label="Requirements"
                                          variant="outlined"
                                          fullWidth
                                        />
                                        <ErrorMessage name="requirements" component="div" />
                                      </Grid>
                                      <Grid item>
                                        <Field
                                          as={TextField}
                                          select
                                          name="contract"
                                          label="Job Type"
                                          variant="outlined"
                                          fullWidth
                                        >
                                          <MenuItem value="Full Time">Full Time</MenuItem>
                                          <MenuItem value="Part Time">Part Time</MenuItem>
                                          <MenuItem value="Work From Home">Work From Home</MenuItem>
                                        </Field>
                                        <ErrorMessage name="contract" component="div" />
                                      </Grid>
                                      <Grid item>
                                        <Field
                                          as={TextField}
                                          name="location"
                                          label="Location"
                                          variant="outlined"
                                          fullWidth
                                        />
                                        <ErrorMessage name="location" component="div" />
                                      </Grid>
                                      <Grid item>
                                                                              <Field
                                                                                as={TextField}
                                                                                name="salary"
                                                                                label="Salary"
                                                                                variant="outlined"
                                                                                fullWidth
                                                                              />
                                                                              <ErrorMessage name="salary" component="div" />
                                                                            </Grid>

                                      <Button
                                                              type="submit"
                                                              variant="contained"
                                                              color="primary"
                                                              style={{ padding: "10px 50px", marginTop: "30px" }}
                                                              disabled={isSubmitting}
                                                            >
                                                              Create Job
                                      </Button>
                                      </Grid>
                                      </Grid>
                                    </Form>
                                    )}
                </Formik>



            </Paper>
          </Grid>
        </Grid>
      </Grid>

    </>
  );
};

export default CreateJobs;