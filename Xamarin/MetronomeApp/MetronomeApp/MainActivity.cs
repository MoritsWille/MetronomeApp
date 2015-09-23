using System;
using System.Diagnostics;

using Android.App;
using Android.Media;
using Android.Content;
using Android.Runtime;
using Android.Views;
using Android.Widget;
using Android.OS;

namespace MetronomeApp
{
	[Activity (Label = "MetronomeApp", MainLauncher = true, Icon = "@drawable/icon")]
	public class MainActivity : Activity
	{
		MediaPlayer _player;
		int BPM;
		long BeforeTime;
		Stopwatch StopWatch = new Stopwatch();

		protected override void OnCreate (Bundle bundle)
		{
			base.OnCreate (bundle);

			_player = MediaPlayer.Create(this, MetronomeApp.Resource.Raw.Metronome1a);

			// Set our view from the "main" layout resource
			SetContentView (MetronomeApp.Resource.Layout.Main);
			//Get ui elements
			Android.Widget.Switch StartSwitch = FindViewById<Android.Widget.Switch>(MetronomeApp.Resource.Id.Switch);
			EditText BPMText = FindViewById<EditText>(MetronomeApp.Resource.Id.BPM);

			StartSwitch.CheckedChange += delegate(object sender, CompoundButton.CheckedChangeEventArgs e) {
				BPM = Convert.ToInt16 (BPMText.Text);

				StopWatch.Start ();
				WatchStart:
				_player.Start ();
				BeforeTime = StopWatch.ElapsedMilliseconds;
				IfStart:
				if (BeforeTime + 60000 / BPM == StopWatch.ElapsedMilliseconds)
					goto WatchStart;
				else
					goto IfStart;
			};
		}
	}
}