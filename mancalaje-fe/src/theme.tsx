import createMuiTheme, {Theme} from "@material-ui/core/styles/createMuiTheme";
import {blue, green, red} from "@material-ui/core/colors";
import {createStyles} from "@material-ui/styles";
import makeStyles from "@material-ui/styles/makeStyles";

export const BLUE_WEIGHT = 500;
export const GREEN_LIGHT_WEIGHT = 100;
export const GREEN_WEIGHT = 600;
export const AMBER_WEIGHT = 700;
export const RED_WEIGHT = 800;
export const XS_COL_SPAN = 12;
export const BOX_MARGIN = 2;
export const GRID_SPACING = 0;


export const useStyles = makeStyles((theme: Theme) => {
    let themeSpacing = 2;
    if (theme.spacing) {
        themeSpacing = theme.spacing(2);
    }
    const newStyles = {
        icon: {
            margin: themeSpacing,
        },
        iconHover: {
            margin: themeSpacing,
            '&:hover': {
                color: red[RED_WEIGHT],
            },
        },
        root: {
            alignItems: 'flex-end',
            borderRadius: '10px',
            display: 'flex',
            justifyContent: 'center',
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
                alignItems: "center",
                borderRadius: 5,
                margin: 10,
                padding: 10,
                width: 800
            },
        },
        MuiButton: {
            root: {
                alignItems: "center",
                minWidth: "100%",
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
                alignContent: "center",
                alignItems: "center",
                borderColor: "black",
                borderWidth: 2,
                fontSize: 50,
                fontWeight: 900,
                height: 71,
                paddingBottom: 14,
                paddingLeft: 16,
                paddingRight: 16,
                paddingTop: 14,
                width: 46,
            },
            body: {
                alignContent: "center",
                alignItems: "center",
                borderWidth: 0,
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
        background: {default: '#000'},
        error: red,
        contrastThreshold: 3,
        primary: {main: blue[BLUE_WEIGHT]},
        secondary: {main: green[GREEN_LIGHT_WEIGHT]},
        tonalOffset: 0.2,
        type: "light",
    }
});

export const control = {
    backgroundColor: '#8de4e1',
    margin: 15,
};

export const holeDisabled = {
    backgroundColor: '#e40010',
    borderRadius: 50
};

export const holeEnabled = {
    backgroundColor: '#00e41d',
    borderRadius: 50
};



