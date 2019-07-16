import createMuiTheme, {Theme} from "@material-ui/core/styles/createMuiTheme";
import {blue, green, red} from "@material-ui/core/colors";
import {createStyles} from "@material-ui/styles";
import makeStyles from "@material-ui/styles/makeStyles";

export const useStyles = makeStyles((theme: Theme) => {
    let themeSpacing = 2;
    if (theme.spacing) {
        themeSpacing = theme.spacing(2);
    }
    let newStyles = {
        icon: {
            margin: themeSpacing,
        },
        iconHover: {
            margin: themeSpacing,
            '&:hover': {
                color: red[800],
            },
        },
        root: {
            display: 'flex',
            justifyContent: 'center',
            alignItems: 'flex-end',
            borderRadius: '10px'
        },
        shape: {
            borderRadius: 50
        }
    };
    return createStyles(newStyles)
});

export const theme = createMuiTheme({
    overrides: {
        MuiTextField: {
            root: {
                borderRadius: 5,
                minWidth: "100%",
            }
        },
        MuiAppBar: {
            root: {
                borderRadius: 5,
                margin: 10,
                padding: 10,
                alignItems: "center",
                width: 800
            },
        },
        MuiButton: {
            root: {
                minWidth: "100%",
                alignItems: "center"
            }
        },
        MuiButtonBase: {
            root: {
                padding: 0,
            }
        },
        MuiList: {
            root: {
                alignItems: "left"
            }

        },
        MuiListItem: {
            root: {
                alignItems: "left",
                padding: 0,
                margin: 0
            }
        },
        MuiTableCell: {
            root: {
                fontSize: 50,
                fontWeight: 900,
                paddingTop: 14,
                paddingBottom: 14,
                paddingLeft: 16,
                paddingRight: 16,
                borderColor: "black",
                borderWidth: 2,
                alignContent: "center",
                alignItems: "center",
                width: 46,
                height: 71
            },
            body: {
                borderWidth: 0,
                alignContent: "center",
                alignItems: "center"
            },

        }
    },
    shape: {
        borderRadius: 50
    },
    typography: {
        fontFamily: [
            'Raleway',
            '-apple-system',
            'BlinkMacSystemFont',
            '"Segoe UI"',
            'Roboto',
            '"Helvetica Neue"',
            'Arial',
            'sans-serif',
            '"Apple Color Emoji"',
            '"Segoe UI Emoji"',
            '"Segoe UI Symbol"',
        ].join(','),
        fontSize: 10
    },
    palette: {
        primary: {main: blue[500],},
        secondary: {main: green[100]},
        error: red,
        contrastThreshold: 3,
        tonalOffset: 0.2,
        type: "light",
        background: {default: '#000'}
    }
});

export const control = {
    margin: 15,
    backgroundColor: '#8de4e1',
};

export const holeDisabled = {
    backgroundColor: '#e40010',
    borderRadius: 50
};

export const holeEnabled = {
    backgroundColor: '#00e41d',
    borderRadius: 50
};

export const GREEN_WEIGHT = 600;
export const AMBER_WEIGHT = 700;

export const XS_COL_SPAN = 12;
