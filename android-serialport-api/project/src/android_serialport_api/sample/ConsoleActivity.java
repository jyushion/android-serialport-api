/*
 * Copyright 2009 Cedric Priscal
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */

package android_serialport_api.sample;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

public class ConsoleActivity extends SerialPortActivity {

	EditText mReception;
	EditText mEmission;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.console);

		mReception = (EditText) findViewById(R.id.EditTextReception);
		mEmission = (EditText) findViewById(R.id.EditTextEmission);
		mEmission.requestFocus();
		findViewById(R.id.SendBtn).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
//				byte[] data = null;
//				try {
//					data = HexDump.hexStringToByteArray(mEmission.getText().toString());
//					if (data.length <= 0) {
//						Toast.makeText(ConsoleActivity.this, "请输入16进制字符", Toast.LENGTH_SHORT).show();
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//					Toast.makeText(ConsoleActivity.this, "请输入16进制字符", Toast.LENGTH_SHORT).show();
//				}
				try {
					//mOutputStream.write(data);
					mOutputStream.write(HexDump.hexStringToByteArray("AA550003FFFC0300FE00FE"));
					Thread.sleep(2000);
					mOutputStream.write(HexDump.hexStringToByteArray("AA550002FFFD03000101"));
				} catch (Exception e) {
					e.printStackTrace();
					Toast.makeText(ConsoleActivity.this, "发送失败", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	@Override
	protected void onDataReceived(final byte[] buffer, final int size) {
		runOnUiThread(new Runnable() {
			public void run() {
				if (mReception != null) {
					mReception.append(HexDump.toHexString(buffer, 0, size));
				}
			}
		});
	}
}
