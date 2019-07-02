import * as React from "react";
import {Grid, Typography} from "@material-ui/core";
import AppBar from "@material-ui/core/AppBar";
import {control} from "../theme";

class MancalaJeHeader extends React.Component {
    render() {
        return (
            <Grid
                container
                spacing={0}
                direction="column"
                alignItems="center"
                justify="center"
                style={{minHeight: '100vh'}}
            >
                <Grid item xs={12}>
                    <AppBar title="Title" position="relative">
                        <Typography align="center" component="h1" variant="h1">MancalaJe</Typography>
                    </AppBar>
                </Grid>
                <Grid item xs={12}>
                    <div style={control}>---</div>
                </Grid>
                {this.props.children}
            </Grid>
        )
    }
}

export default MancalaJeHeader;