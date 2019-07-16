import * as React from "react";
import {Grid, Typography} from "@material-ui/core";
import AppBar from "@material-ui/core/AppBar";
import Box from "@material-ui/core/Box";
import {BOX_MARGIN, GRID_SPACING, XS_COL_SPAN} from "../theme";

class MancalaJeHeader extends React.Component {
    render() {
        return (
            <Grid
                container
                spacing={GRID_SPACING}
                direction="column"
                alignItems="center"
                justify="center"
                style={{minHeight: '100vh'}}>
                <Grid item xs={XS_COL_SPAN}>
                    <Box margin={BOX_MARGIN}>
                        <AppBar title="Title" position="relative">
                            <Typography align="center" component="h1" variant="h1">MancalaJe</Typography>
                        </AppBar>
                    </Box>
                </Grid>
                <Grid item xs={XS_COL_SPAN}/>
                {this.props.children}
            </Grid>
        )
    }
}

export {MancalaJeHeader};

