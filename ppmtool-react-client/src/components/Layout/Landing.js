import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import Proptypes from 'prop-types';
import { connect } from 'react-redux';

class Landing extends Component {

    //while a user is signed in, redirect to this component
    componentDidMount(){
        if(this.props.security.validToken){
            this.props.history.push("/dashboard")
        }
    }

    render() {
        return (
            <div className="landing">
            <div className="Light-overlay landing-inner text-dark ">
                <div className="container">
                    <div className="row">
                        <div className="col-md-12 text-center">
                            <h1 className="display-3 mb-4">Personal Project Management Tool</h1>
                            <p className="lead">
                                Create your account to join active projects or start your own
                            </p>
                            <hr />
                            <Link to="/register" className="btn btn-lg btn-primary mr-2">
                                Sign Up
                            </Link>
                            <Link to="/login" className="btn btn-lg btn-secondary mr-2">
                                Login
                            </Link>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        )
    }
}

Landing.protype = {
    security: Proptypes.object.isRequired
}

const mapStateToProps = (state) => ({
    security: state.security
})

export default connect(mapStateToProps)(Landing)