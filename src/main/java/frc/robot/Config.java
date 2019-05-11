package frc.robot;

import edu.wpi.first.wpilibj.Preferences;

public class Config
{

  static private Config       config = null;
  static private Preferences preferences = null;

  private Config()
  {
    // Get the preferences singleton
    preferences = Preferences.getInstance();
  }

  public static Config getInstance()
  {
    // If we haven't already instantiated a Config object, instantiate one right now.
    if (config == null)
    {
      config = new Config();
    }
    
    // Return the previously- or just-instantiated Config object
    return config;
  }

  /**
   * Returns the string at the given key. If this table does not have a value for that position,
   * then the given backup value will be saved and returned.
   *
   * @param key    the key
   * @param backup the value to return if none exists in the table
   * @return either the value in the table, or the backup
   */
  public String getString(String key, String backup) 
  {
    // If this key hasn't been saved...
    if (! preferences.containsKey(key))
    {
        // ... then save our backup
        preferences.putString(key, backup);
    }

    // Retrieve the saved value. `backup` will never be used here.
    return preferences.getString(key, backup);
  }

  /**
   * Returns the int at the given key. If this table does not have a value for that position, then
   * the given backup value will be saved and returned.
   *
   * @param key    the key
   * @param backup the value to return if none exists in the table
   * @return either the value in the table, or the backup
   */
  public int getInt(String key, int backup) 
  {
    // If this key hasn't been saved...
    if (! preferences.containsKey(key))
    {
        // ... then save our backup
        preferences.putInt(key, backup);
    }
    
    // Retrieve the saved value. `backup` will never be used here.
    return preferences.getInt(key, backup);
  }

  /**
   * Returns the double at the given key. If this table does not have a value for that position,
   * then the given backup value will be saved and returned.
   *
   * @param key    the key
   * @param backup the value to return if none exists in the table
   * @return either the value in the table, or the backup
   */
  public double getDouble(String key, double backup) 
  {
    // If this key hasn't been saved...
    if (!preferences.containsKey(key)) {
      // ... then save our backup
      preferences.putDouble(key, backup);
    }

    // Retrieve the saved value. `backup` will never be used here.
    return preferences.getDouble(key, backup);
}

  /**
   * Returns the boolean at the given key. If this table does not have a value for that position,
   * then the given backup value will be saved and returned.
   *
   * @param key    the key
   * @param backup the value to return if none exists in the table
   * @return either the value in the table, or the backup
   */
  public boolean getBoolean(String key, boolean backup) 
  {
    // If this key hasn't been saved...
    if (!preferences.containsKey(key)) {
      // ... then save our backup
      preferences.putBoolean(key, backup);
    }

    // Retrieve the saved value. `backup` will never be used here.
    return preferences.getBoolean(key, backup);
  }

  /**
   * Returns the float at the given key. If this table does not have a value for that position, then
   * the given backup value will be saved and returned.
   *
   * @param key    the key
   * @param backup the value to return if none exists in the table
   * @return either the value in the table, or the backup
   */
  public float getFloat(String key, float backup) 
  {
    // If this key hasn't been saved...
    if (!preferences.containsKey(key)) {
      // ... then save our backup
      preferences.putFloat(key, backup);
    }

    // Retrieve the saved value. `backup` will never be used here.
    return preferences.getFloat(key, backup);
  }

  /**
   * Returns the long at the given key. If this table does not have a value for that position, then
   * the given backup value will be saved and returned.
   *
   * @param key    the key
   * @param backup the value to return if none exists in the table
   * @return either the value in the table, or the backup
   */
  public long getLong(String key, long backup) 
  {
    // If this key hasn't been saved...
    if (!preferences.containsKey(key)) {
      // ... then save our backup
      preferences.putLong(key, backup);
    }

    // Retrieve the saved value. `backup` will never be used here.
    return preferences.getLong(key, backup);
  }
}