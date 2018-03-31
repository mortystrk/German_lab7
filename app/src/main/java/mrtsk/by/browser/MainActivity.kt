package mrtsk.by.browser

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var links = ArrayList<String>()
    private lateinit var selectedLink: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        links.add("https://www.google.by")
        links.add("https://www.youtube.com")
        links.add("https://twitter.com")

        val linksAdapter = LinkAdapter(this, links)
        lvLinks.adapter = linksAdapter

        lvLinks.setOnItemClickListener {parent, view, position, id ->
            selectedLink = links[position]
        }

        btnGoToLink.setOnClickListener {
            val intent = Intent("WebActivity")
            intent.data = Uri.parse(selectedLink)
            startActivity(intent)
        }
    }

    inner class LinkAdapter(context: Context, private var linksList: ArrayList<String>) : BaseAdapter() {

        private var context: Context? = context

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
            val view: View?
            val vh: ViewHolder

            if (convertView == null) {
                view = layoutInflater.inflate(R.layout.item, parent, false )
                vh = ViewHolder(view)
                view.tag = vh
            } else {
                view = convertView
                vh = view.tag as ViewHolder
            }

            vh.link.text = linksList[position]

            return view
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {
            return linksList.size
        }

        override fun getItem(p0: Int): Any {
            return links[p0]
        }


    }

    private class ViewHolder(view: View?) {
        val link: TextView = view?.findViewById<TextView>(R.id.tvLink) as TextView
    }
}
