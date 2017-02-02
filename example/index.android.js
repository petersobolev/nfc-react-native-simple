import React, { Component } from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  View,
  NativeModules,  
  DeviceEventEmitter
} from 'react-native';

export default class nfcExample extends Component {

  constructor(props)
  {
      super(props);
      this.state = {
        nfc_id:'',
      };
  }

componentDidMount ()
{
  var nfcListener = DeviceEventEmitter.addListener('NFCCardID', (data) =>
  {
    console.log(data);

    this.setState({ nfc_id: data.id });
  });


}


  render() {
    return (
      <View style={styles.container}>
        <Text style={styles.message}>
          React Native nfc test. Touch your phone with NFC card. 
        </Text>
        <Text style={styles.message}>
          nfc id: {this.state.nfc_id}
        </Text>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  message: {
    fontSize: 20,
    textAlign: 'center',
    color: '#333333',
    marginBottom: 15,
  },
});

AppRegistry.registerComponent('nfcExample', () => nfcExample);
